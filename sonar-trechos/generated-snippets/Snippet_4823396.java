@SuppressWarnings("serial")
public abstract class ContextualView extends JPanel {
	/**
	 * When implemented, this method should define the main frame that is placed
	 * in this container, and provides a static view of the Dataflow element.
	 * 
	 * @return a JComponent that represents the dataflow element.
	 */
	public abstract JComponent getMainFrame();

	/**
	 * @return a String providing a title for the view
	 */
	public abstract String getViewTitle();

	/**
	 * Allows the item to be configured, but returning an action handler that
	 * will be invoked when selecting to configure. By default this is provided
	 * by a button.
	 * <p>
	 * If there is no ability to configure the given item, then this should
	 * return null.
	 * 
	 * @param owner
	 *            the owning dialog to be used when displaying dialogues for
	 *            configuration options
	 * @return an action that allows the element being viewed to be configured.
	 */
	public Action getConfigureAction(Frame owner) {
		return null;
	}

	/**
	 * This <i>must</i> be called by any sub-classes after they have initialised
	 * their own view since it gets their main panel and adds it to the main
	 * contextual view. If you don't do this you will get a very empty frame
	 * popping up!
	 */
	public void initView() {
		setLayout(new BorderLayout());
		add(getMainFrame(), CENTER);
		setName(getViewTitle());
	}

	public abstract void refreshView();

	public abstract int getPreferredPosition();

	public static String getTextFromDepth(String kind, Integer depth) {
		String labelText = "The last prediction said the " + kind;
		if (depth == null) {
			labelText += " would not transmit a value";
		} else if (depth == -1) {
			labelText += " was invalid/unpredicted";
		} else if (depth == 0) {
			labelText += " would carry a single value";
		} else {
			labelText += " would carry a list of depth " + depth;
		}
		return labelText;
	}
}
