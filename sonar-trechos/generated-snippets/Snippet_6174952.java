public class Snippet__6174952 {

    @Override
      public DebugConfiguration createConfiguration(
          String typeId, String name, String host, int port, Map<String, String> connectionProperties) {
        final DebugConfigurationType configurationType =
            configurationTypeRegistry.getConfigurationTypeById(typeId);

        final DebugConfiguration configuration =
            new DebugConfiguration(
                configurationType,
                generateUniqueConfigurationName(configurationType, name),
                host,
                port,
                connectionProperties);
        configurations.add(configuration);
        saveConfigurations();
        fireConfigurationAdded(configuration);

        return configuration;
      }

}
