public class Snippet__5235274 {

    public Snippet__5235274(int port) {
            this.port = port;

            List<String> propertiesFiles = new ArrayList<>();
            // set up the configuration, if there is any
            if (System.getProperty("org.apache.oodt.cas.resource.properties") != null) {
                propertiesFiles.add(System.getProperty("org.apache.oodt.cas.resource.properties"));
            }

            configurationManager = ConfigurationManagerFactory
                    .getConfigurationManager(Component.RESOURCE_MANAGER, propertiesFiles);
        }

}
