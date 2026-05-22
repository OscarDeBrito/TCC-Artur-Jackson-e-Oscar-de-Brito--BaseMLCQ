private static class PropertiesLookup extends StrLookup<Object> {
        private static final Map<String, String> ENV = System.getenv();

        @Override
        public synchronized String lookup(final String key) {
            String value = SystemInstance.get().getProperties().getProperty(key);
            if (value != null) {
                return value;
            }

            value = ENV.get(key);
            if (value != null) {
                return value;
            }

            return null;
        }

        public synchronized void reload() {
            //no-op
        }
    }
