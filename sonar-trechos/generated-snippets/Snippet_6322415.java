public class WriteRequest implements Validable {

    private String name;
    private DataType type;
    private String value;

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

    public TypedValue<?> getValue() {
        return TypedValues.parseTypedValue(type, value);
    }

    public ChannelRecord toChannelRecord() {
        return ChannelRecord.createWriteRecord(name, getValue());
    }

    @Override
    public String toString() {
        return "WriteRequest [name=" + name + ", type=" + type + ", value=" + getValue() + "]";
    }

    @Override
    public boolean isValid() {
        return name != null && type != null && value != null;
    }

}
