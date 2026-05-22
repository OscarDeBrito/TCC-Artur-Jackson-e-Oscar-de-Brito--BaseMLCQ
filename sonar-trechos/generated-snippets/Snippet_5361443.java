public class Snippet__5361443 {

    @SuppressWarnings("unchecked")
        public <T extends Enum<T>> T get(final String property, final T defaultValue) {
            final String value = properties.getProperty(property);

            if (value == null || value.equals(""))
                return parent.get(property, defaultValue);

            if (defaultValue == null)
                throw new IllegalArgumentException("Must supply a default for property " + property);

            final Class<T> enumType = (Class<T>) defaultValue.getClass();

            try {
                return log(property, valueOf(enumType, value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                warn(property, value);
                return parent.get(property, defaultValue);
            }
        }

}
