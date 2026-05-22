public class BulkSetSerializer extends SimpleTypeSerializer<BulkSet> {
    public BulkSetSerializer() {
        super(DataType.BULKSET);
    }

    @Override
    protected BulkSet readValue(final ByteBuf buffer, final GraphBinaryReader context) throws SerializationException {
        final int length = buffer.readInt();

        final BulkSet result = new BulkSet();
        for (int i = 0; i < length; i++) {
            result.add(context.read(buffer), buffer.readLong());
        }

        return result;
    }

    @Override
    protected void writeValue(final BulkSet value, final ByteBuf buffer, final GraphBinaryWriter context) throws SerializationException {
        final Map<Object,Long> raw = value.asBulk();
        buffer.writeInt(raw.size());

        for (Object key : raw.keySet()) {
            context.write(key, buffer);
            buffer.writeLong(value.get(key));
        }
    }
}
