private static class ModelElementDetermingModelVisitor implements IModelElementVisitor {

		private int startLine;

		private int endLine;

		private final IFile file;

		private IModelElement element;

		public ModelElementDetermingModelVisitor(final int startLine, final int endLine, final IFile file) {
			if (startLine + 1 == endLine) {
				this.startLine = startLine + 1;
			}
			else {
				this.startLine = startLine;
			}
			this.endLine = endLine;
			this.file = file;
		}

		public IModelElement getElement() {
			return element;
		}

		public boolean visit(IModelElement element, IProgressMonitor monitor) {
			if (element instanceof ISourceModelElement) {
				ISourceModelElement sourceElement = (ISourceModelElement) element;
				if (sourceElement.getElementResource().equals(file)
						&& (sourceElement.getElementStartLine() <= startLine || sourceElement.getElementStartLine() - 1 <= startLine)
						&& endLine <= sourceElement.getElementEndLine()) {
					this.element = element;

					if (sourceElement.getElementStartLine() == startLine
							&& endLine == sourceElement.getElementEndLine()) {
						startLine = -1;
						endLine = -1;
						return false;
					}
					return true;
				}
				return false;
			}
			else if (element instanceof IBeansConfig) {
				return true;
			}
			else {
				return false;
			}
		}
	}
