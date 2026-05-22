public class NamedField extends Field {
  final MajorType keyType;
  String fieldName;

  public NamedField(RecordSchema parentSchema, String prefixFieldName, String fieldName, MajorType fieldType) {
    this(parentSchema, prefixFieldName, fieldName, fieldType, JacksonHelper.STRING_TYPE);
  }

  public NamedField(RecordSchema parentSchema,
                    String prefixFieldName,
                    String fieldName,
                    MajorType fieldType,
                    MajorType keyType) {
    super(parentSchema, fieldType, prefixFieldName);
    this.fieldName = fieldName;
    this.keyType = keyType;
  }

  @Override
  public String getFieldName() {
    return fieldName;
  }

  @Override
  protected MoreObjects.ToStringHelper addAttributesToHelper(MoreObjects.ToStringHelper helper) {
    return helper.add("keyType", keyType);
  }
}
