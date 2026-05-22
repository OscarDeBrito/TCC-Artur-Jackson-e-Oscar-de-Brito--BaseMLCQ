public class SortedDocValuesField extends Field {

  /**
   * Type for sorted bytes DocValues
   */
  public static final FieldType TYPE = new FieldType();
  static {
    TYPE.setDocValuesType(DocValuesType.SORTED);
    TYPE.freeze();
  }

  /**
   * Create a new sorted DocValues field.
   * @param name field name
   * @param bytes binary content
   * @throws IllegalArgumentException if the field name is null
   */
  public SortedDocValuesField(String name, BytesRef bytes) {
    super(name, TYPE);
    fieldsData = bytes;
  }

  /**
   * Create a range query that matches all documents whose value is between
   * {@code lowerValue} and {@code upperValue} included.
   * <p>
   * You can have half-open ranges by setting {@code lowerValue = null}
   * or {@code upperValue = null}.
   * <p><b>NOTE</b>: Such queries cannot efficiently advance to the next match,
   * which makes them slow if they are not ANDed with a selective query. As a
   * consequence, they are best used wrapped in an {@link IndexOrDocValuesQuery},
   * alongside a range query that executes on points, such as
   * {@link BinaryPoint#newRangeQuery}.
   */
  public static Query newSlowRangeQuery(String field,
      BytesRef lowerValue, BytesRef upperValue,
      boolean lowerInclusive, boolean upperInclusive) {
    return new SortedSetDocValuesRangeQuery(field, lowerValue, upperValue, lowerInclusive, upperInclusive) {
      @Override
      SortedSetDocValues getValues(LeafReader reader, String field) throws IOException {
        return DocValues.singleton(DocValues.getSorted(reader, field));
      }
    };
  }

  /** 
   * Create a query for matching an exact {@link BytesRef} value.
   * <p><b>NOTE</b>: Such queries cannot efficiently advance to the next match,
   * which makes them slow if they are not ANDed with a selective query. As a
   * consequence, they are best used wrapped in an {@link IndexOrDocValuesQuery},
   * alongside a range query that executes on points, such as
   * {@link BinaryPoint#newExactQuery}.
   */
  public static Query newSlowExactQuery(String field, BytesRef value) {
    return newSlowRangeQuery(field, value, value, true, true);
  }
}
