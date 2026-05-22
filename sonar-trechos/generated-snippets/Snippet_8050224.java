public class Delegate implements ITemplateAcceptor {

		private ITemplateAcceptor delegate;
		
		@Override
		public void accept(TemplateProposal template) {
			delegate.accept(template);
		}

		@Override
		public boolean canAcceptMoreTemplates() {
			return delegate.canAcceptMoreTemplates();
		}

		public void setDelegate(ITemplateAcceptor delegate) {
			this.delegate = delegate;
		}

		public ITemplateAcceptor getDelegate() {
			return delegate;
		}

	}
