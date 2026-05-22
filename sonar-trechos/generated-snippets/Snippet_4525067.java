public interface BackingStore {

    /**
     * Store the current preferences and its children in the backing
     * store.
     * The store should check, if the preferences have changed,
     * it should also check all children.
     * @param prefs The preferences.
     * @throws BackingStoreException
     */
    void store(PreferencesImpl prefs) throws BackingStoreException;

    /**
     * Update the current preferences and its children from the
     * backing store.
     */
    void update(PreferencesImpl prefs) throws BackingStoreException;

    /**
     * Return all bundle ids for which preferences are stored..
     * @return Return an array of bundle ids or an empty array.
     */
    Long[] availableBundles();

    /**
     * Remove all preferences stored for this bundle.
     * @param bundleId The bundle id.
     * @throws BackingStoreException
     */
    void remove(Long bundleId) throws BackingStoreException;

    /**
     * Load the preferences for the given description.
     * @param manager The backing store manager which should be passed to new preferences implementations.
     * @param desc
     * @return A new preferences object or null if it's not available in the backing store.
     * @throws BackingStoreException
     */
    PreferencesImpl load(BackingStoreManager manager, PreferencesDescription desc) throws BackingStoreException;

    /**
     * Load all preferences for this bundle.
     * @param manager The backing store manager which should be passed to new preferences implementations.
     * @param bundleId The bundle id.
     * @return An array with the preferences or an empty array.
     * @throws BackingStoreException
     */
    PreferencesImpl[] loadAll(BackingStoreManager manager, Long bundleId) throws BackingStoreException;
}
