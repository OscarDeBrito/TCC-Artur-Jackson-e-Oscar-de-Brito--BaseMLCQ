public class Snippet__8443651 {

    @Override
        protected void doStart() throws Exception {
            if (gson == null) {
                GsonBuilder builder = new GsonBuilder();
                if (exclusionStrategies != null && !exclusionStrategies.isEmpty()) {
                    ExclusionStrategy[] strategies = exclusionStrategies.toArray(new ExclusionStrategy[exclusionStrategies.size()]);
                    builder.setExclusionStrategies(strategies);
                }
                if (longSerializationPolicy != null) {
                    builder.setLongSerializationPolicy(longSerializationPolicy);
                }
                if (fieldNamingPolicy != null) {
                    builder.setFieldNamingPolicy(fieldNamingPolicy);
                }
                if (fieldNamingStrategy != null) {
                    builder.setFieldNamingStrategy(fieldNamingStrategy);
                }
                if (serializeNulls) {
                    builder.serializeNulls();
                }
                if (prettyPrint) {
                    builder.setPrettyPrinting();
                }
                if (dateFormatPattern != null) {
                    builder.setDateFormat(dateFormatPattern);
                }
                gson = builder.create();
            }
        }

}
