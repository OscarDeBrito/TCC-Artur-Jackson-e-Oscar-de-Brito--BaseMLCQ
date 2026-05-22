public class Snippet__3974746 {

    @Override
      public boolean validate(Schema schema, Object datum) {
        switch (schema.getType()) {
        case ARRAY:
          if (!datum.getClass().isArray())
            return super.validate(schema, datum);
          int length = java.lang.reflect.Array.getLength(datum);
          for (int i = 0; i < length; i++)
            if (!validate(schema.getElementType(),
                          java.lang.reflect.Array.get(datum, i)))
              return false;
          return true;
        default:
          return super.validate(schema, datum);
        }
      }

}
