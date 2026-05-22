public abstract class FlowContainerLayout extends FlowFigureLayout implements
		FlowContext {

	/**
	 * the current line
	 */
	LineBox currentLine;

	/**
	 * @see org.eclipse.draw2d.text.FlowFigureLayout#FlowFigureLayout(FlowFigure)
	 */
	protected FlowContainerLayout(FlowFigure flowFigure) {
		super(flowFigure);
	}

	/**
	 * Adds the given box the current line and clears the context's state.
	 * 
	 * @see org.eclipse.draw2d.text.FlowContext#addToCurrentLine(FlowBox)
	 */
	public void addToCurrentLine(FlowBox child) {
		getCurrentLine().add(child);
		setContinueOnSameLine(false);
	}

	/**
	 * Flush anything pending and free all temporary data used during layout.
	 */
	protected void cleanup() {
		currentLine = null;
	}

	/**
	 * Used by getCurrentLine().
	 */
	protected abstract void createNewLine();

	/**
	 * Called after {@link #layoutChildren()} when all children have been laid
	 * out. This method exists to flush the last line.
	 */
	protected abstract void flush();

	/**
	 * FlowBoxes shouldn't be added directly to the current line. Use
	 * {@link #addToCurrentLine(FlowBox)} for that.
	 * 
	 * @see org.eclipse.draw2d.text.FlowContext#getCurrentLine()
	 */
	LineBox getCurrentLine() {
		if (currentLine == null)
			createNewLine();
		return currentLine;
	}

	/**
	 * @see FlowContext#getRemainingLineWidth()
	 */
	public int getRemainingLineWidth() {
		return getCurrentLine().getAvailableWidth();
	}

	/**
	 * @see FlowContext#isCurrentLineOccupied()
	 */
	public boolean isCurrentLineOccupied() {
		return currentLine != null && currentLine.isOccupied();
	}

	/**
	 * @see FlowFigureLayout#layout()
	 */
	protected void layout() {
		preLayout();
		layoutChildren();
		flush();
		cleanup();
	}

	/**
	 * Layout all children.
	 */
	protected void layoutChildren() {
		List children = getFlowFigure().getChildren();
		for (int i = 0; i < children.size(); i++) {
			Figure f = (Figure) children.get(i);
			if (forceChildInvalidation(f))
				f.invalidate();
			f.validate();
		}
	}

	boolean forceChildInvalidation(Figure f) {
		return true;
	}

	/**
	 * Called before layoutChildren() to setup any necessary state.
	 */
	protected abstract void preLayout();

}
