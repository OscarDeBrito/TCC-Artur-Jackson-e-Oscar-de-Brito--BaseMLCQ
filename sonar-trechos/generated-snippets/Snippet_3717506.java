public interface BundleListener extends EventListener {
	/**
	 * Receives notification that a bundle has had a lifecycle change.
	 * 
	 * @param event
	 *            The <tt>BundleEvent</tt>.
	 */
	public abstract void bundleChanged(final BundleEvent event);
}
