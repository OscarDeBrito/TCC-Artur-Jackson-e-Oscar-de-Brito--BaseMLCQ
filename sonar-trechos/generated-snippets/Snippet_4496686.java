public class Snippet__4496686 {

    @SuppressWarnings("unchecked")
        public SinkProcessorConfiguration getConfiguration(String name)
            throws ConfigurationException {
          Class<? extends SinkProcessorConfiguration> clazz;
          SinkProcessorConfiguration instance = null;
          try {
            if (processorClassName != null) {
              clazz =
                  (Class<? extends SinkProcessorConfiguration>) Class
                      .forName(processorClassName);
              instance = clazz.getConstructor(String.class).newInstance(name);

            } else {
              return new SinkProcessorConfiguration(name);
            }
          } catch (ClassNotFoundException e) {
            // Could not find the configuration stub, do basic validation
            instance = new SinkProcessorConfiguration(name);
            // Let the caller know that this was created because of this exception.
            instance.setNotFoundConfigClass();
          } catch (Exception e) {
            throw new ConfigurationException(
                "Could not instantiate configuration!", e);
          }
          return instance;
        }

}
