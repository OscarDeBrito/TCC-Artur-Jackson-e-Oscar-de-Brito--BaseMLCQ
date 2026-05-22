public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.microsoft.azuretools.hdinsight"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

        com.microsoft.azuretools.azureexplorer.helpers.HDInsightHelperImpl.initHDInsightLoader();

		String enabledProperty = DefaultLoader.getIdeHelper().getProperty(Messages.HDInsightFeatureEnabled);
		if(StringHelper.isNullOrWhiteSpace(enabledProperty)) {
			AppInsightsClient.create(Messages.HDInsightFeatureEnabled, context.getBundle().getVersion().toString());
			DefaultLoader.getIdeHelper().setProperty(Messages.HDInsightFeatureEnabled, "true");
		}
		HDInsightJobViewUtils.checkInitlize();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		HDInsightJobViewUtils.closeJobViewHttpServer();
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public void log(String message, Throwable excp) {
        getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message, excp));
    }
}
