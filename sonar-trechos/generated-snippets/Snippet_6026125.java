public final class ExecutionViewState {

    private static final String PREF_HEADER_NAME_COLUMN_WIDTH = "executionsView.headerNameColumnWidth"; //$NON-NLS-1$
    private static final String PREF_HEADER_DURATION_COLUMN_WIDTH = "executionsView.headerDurationColumnWidth"; //$NON-NLS-1$

    private int headerNameColumnWidth;
    private int headerDurationColumnWidth;

    public void load() {
        IEclipsePreferences prefs = EclipsePreferencesUtils.getInstanceScope().getNode(UiPlugin.PLUGIN_ID);
        this.headerNameColumnWidth = prefs.getInt(PREF_HEADER_NAME_COLUMN_WIDTH, 600);
        this.headerDurationColumnWidth = prefs.getInt(PREF_HEADER_DURATION_COLUMN_WIDTH, 100);
    }

    public void save() {
        IEclipsePreferences prefs = EclipsePreferencesUtils.getInstanceScope().getNode(UiPlugin.PLUGIN_ID);
        prefs.putInt(PREF_HEADER_NAME_COLUMN_WIDTH, this.headerNameColumnWidth);
        prefs.putInt(PREF_HEADER_DURATION_COLUMN_WIDTH, this.headerDurationColumnWidth);

        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            UiPlugin.logger().error("Unable to store execution view preferences.", e); //$NON-NLS-1$
        }
    }

    public int getHeaderNameColumnWidth() {
        return this.headerNameColumnWidth;
    }

    public void setHeaderNameColumnWidth(int headerNameColumnWidth) {
        this.headerNameColumnWidth = headerNameColumnWidth;
    }

    public int getHeaderDurationColumnWidth() {
        return this.headerDurationColumnWidth;
    }

    public void setHeaderDurationColumnWidth(int headerDurationColumnWidth) {
        this.headerDurationColumnWidth = headerDurationColumnWidth;
    }

    public void dispose() {
        save();
    }

}
