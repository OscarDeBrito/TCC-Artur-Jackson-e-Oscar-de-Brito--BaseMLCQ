public class Snippet__7906258 {

    public static _Fields findByThriftIdOrThrow(int fieldId) {
          _Fields fields = findByThriftId(fieldId);
          if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
          return fields;
        }

}
