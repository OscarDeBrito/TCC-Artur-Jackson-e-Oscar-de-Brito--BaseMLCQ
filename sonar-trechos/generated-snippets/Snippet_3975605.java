public class Snippet__3975605 {

    private static JsonNode validateDefault(String fieldName, Schema schema,
                                              JsonNode defaultValue) {
        if (VALIDATE_DEFAULTS.get() && (defaultValue != null)
            && !isValidDefault(schema, defaultValue)) { // invalid default
          String message = "Invalid default for field "+fieldName
            +": "+defaultValue+" not a "+schema;
          throw new AvroTypeException(message);     // throw exception
        }
        return defaultValue;
      }

}
