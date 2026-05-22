public class PaletteToolbar extends PaletteContainer {

	/**
	 * Type Identifier for a palette group that looks like a toolbar and only
	 * supports icons mode.
	 * 
	 * @since 3.4
	 */
	public static final String PALETTE_TYPE_TOOLBAR_GROUP = "Palette_Toolbar_Group";//$NON-NLS-1$

	/**
	 * Creates a new PaletteGroup with the given label
	 * 
	 * @param label
	 *            the label
	 */
	public PaletteToolbar(String label) {
		super(label, null, null, PALETTE_TYPE_TOOLBAR_GROUP);
		setUserModificationPermission(PERMISSION_NO_MODIFICATION);
	}

	/**
	 * Creates a new PaletteGroup with the given label and list of
	 * {@link PaletteEntry Palette Entries}.
	 * 
	 * @param label
	 *            the label
	 * @param children
	 *            the list of PaletteEntry children
	 */
	public PaletteToolbar(String label, List children) {
		this(label);
		addAll(children);
	}

}
