public class Snippet__7725810 {

    String get(String subsystem, String name, boolean verboseResolution) {
            String key = buildKey(subsystem, name);
            String value = getProperty(key);
            if (value == null) {
                key = toSnakeUpperCase(Utils.PROPERTY_NAME_PREFIX + subsystem).replace(".", "");
                key = key + '_' + toSnakeUpperCase(name);
                value = getEnv(key);
                if (value != null) {
                    logFoundEnvVar(verboseResolution, key, value);
                }
            } else {
                logFoundSystemProperty(verboseResolution, key, value);
            }

            if (value == null || value.length() == 0) {
                return null;
            }

            return value;
        }

}
