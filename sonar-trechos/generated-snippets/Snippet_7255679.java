public class Snippet__7255679 {

    private void initPluginService(String pluginPath) {
            if (pluginPath.length() > 0) {
                try {
                    ClassLoader pluginCL = new URLClassLoader(
                            pathToURLs(pluginPath),
                            JConsolePluginWrapper.class.getClassLoader());
                    ServiceLoader<JConsolePlugin> plugins =
                            ServiceLoader.load(JConsolePlugin.class, pluginCL);
                    // Validate all plugins
                    for (JConsolePlugin p : plugins) {
                        LOGGER.finer("JConsole plugin " + p.getClass().getName() + " loaded."); // NOI18N
                    }
                    pluginService = plugins;
                } catch (ServiceConfigurationError e) {
                    // Error occurs during initialization of plugin
                    LOGGER.warning("Fail to load JConsole plugin: " + e.getMessage()); // NOI18N
                    LOGGER.throwing(JConsolePluginWrapper.class.getName(), "initPluginService", e); // NOI18N
                } catch (MalformedURLException e) {
                    LOGGER.warning("Invalid JConsole plugin path: " + e.getMessage()); // NOI18N
                    LOGGER.throwing(JConsolePluginWrapper.class.getName(), "initPluginService", e); // NOI18N
                }
            }
            if (pluginService == null) {
                initEmptyPlugin();
            }
        }

}
