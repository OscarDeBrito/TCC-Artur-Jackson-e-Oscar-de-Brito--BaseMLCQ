public class Snippet__8908782 {

    public static _Fields findByThriftIdOrThrow(int fieldId) {
          _Fields fields = findByThriftId(fieldId);
          if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
          return fields;
        }

}
