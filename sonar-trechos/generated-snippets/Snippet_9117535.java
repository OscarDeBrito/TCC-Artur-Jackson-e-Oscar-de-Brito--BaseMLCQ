public interface IModelAdapterHelper
{

	/**
	 * Marks the flag
	 * 
	 * @param bool
	 */
	void markDirty( boolean bool );

	/**
	 * Gets the flag
	 * 
	 * @return
	 */
	boolean isDirty( );

	/**
	 * Gets the preferred size
	 * 
	 * @return
	 */
	Dimension getPreferredSize( );

	/**
	 * Gets the insets
	 * 
	 * @return
	 */
	Insets getInsets( );
}
