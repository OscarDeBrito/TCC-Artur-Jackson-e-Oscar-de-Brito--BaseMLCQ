public class BendpointCreationHandle extends BendpointHandle {

	{
		setCursor(SharedCursors.SIZEALL);
		setPreferredSize(new Dimension(DEFAULT_HANDLE_SIZE - 2,
				DEFAULT_HANDLE_SIZE - 2));
	}

	/**
	 * Creates a new BendpointCreationHandle.
	 */
	public BendpointCreationHandle() {
	}

	/**
	 * Creates a new BendpointCreationHandle, sets its owner to
	 * <code>owner</code> and its index to <code>index</code>, and sets its
	 * locator to a new {@link MidpointLocator}.
	 * 
	 * @param owner
	 *            the ConnectionEditPart owner
	 * @param index
	 *            the index
	 */
	public BendpointCreationHandle(ConnectionEditPart owner, int index) {
		setOwner(owner);
		setIndex(index);
		setLocator(new MidpointLocator(getConnection(), index));
	}

	/**
	 * Creates a new BendpointCreationHandle, sets its owner to
	 * <code>owner</code> and its index to <code>index</code>, and sets its
	 * locator to a new {@link MidpointLocator} with the given
	 * <code>locatorIndex</code>.
	 * 
	 * @param owner
	 *            the ConnectionEditPart owner
	 * @param index
	 *            the index
	 * @param locatorIndex
	 *            the locator index
	 */
	public BendpointCreationHandle(ConnectionEditPart owner, int index,
			int locatorIndex) {
		setOwner(owner);
		setIndex(index);
		setLocator(new MidpointLocator(getConnection(), locatorIndex));
	}

	/**
	 * Creates a new BendpointCreationHandle and sets its owner to
	 * <code>owner</code>, sets its index to <code>index</code>, and sets its
	 * locator to <code>locator</code>.
	 * 
	 * @param owner
	 *            the ConnectionEditPart owner
	 * @param index
	 *            the index
	 * @param locator
	 *            the Locator
	 */
	public BendpointCreationHandle(ConnectionEditPart owner, int index,
			Locator locator) {
		setOwner(owner);
		setIndex(index);
		setLocator(locator);
	}

	/**
	 * Creates and returns a new {@link ConnectionBendpointTracker}.
	 * 
	 * @return the new ConnectionBendpointTracker
	 */
	protected DragTracker createDragTracker() {
		ConnectionBendpointTracker tracker;
		tracker = new ConnectionBendpointTracker(
				(ConnectionEditPart) getOwner(), getIndex());
		tracker.setType(RequestConstants.REQ_CREATE_BENDPOINT);
		tracker.setDefaultCursor(getCursor());
		return tracker;
	}

}
