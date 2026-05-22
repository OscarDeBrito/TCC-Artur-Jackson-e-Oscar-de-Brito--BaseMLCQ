public class RecordColumnarIndexer implements IExternalIndexer {

    private static final long serialVersionUID = 1L;
    public static final int NUM_OF_FIELDS = 3;
    protected final AMutableInt32 fileNumber = new AMutableInt32(0);
    protected final AMutableInt64 offset = new AMutableInt64(0);
    protected long nextOffset;
    protected final AMutableInt32 rowNumber = new AMutableInt32(0);
    protected RecordReader<?, Writable> recordReader;

    @SuppressWarnings("unchecked")
    private ISerializerDeserializer<IAObject> intSerde =
            SerializerDeserializerProvider.INSTANCE.getSerializerDeserializer(BuiltinType.AINT32);
    @SuppressWarnings("unchecked")
    private ISerializerDeserializer<IAObject> longSerde =
            SerializerDeserializerProvider.INSTANCE.getSerializerDeserializer(BuiltinType.AINT64);

    @Override
    public void reset(IIndexingDatasource reader) throws HyracksDataException {
        try {
            //TODO: Make this more generic. right now, it works because we only index hdfs files.
            @SuppressWarnings("unchecked")
            HDFSRecordReader<?, Writable> hdfsReader = (HDFSRecordReader<?, Writable>) reader;
            fileNumber.setValue(hdfsReader.getSnapshot().get(hdfsReader.getCurrentSplitIndex()).getFileNumber());
            recordReader = hdfsReader.getReader();
            offset.setValue(recordReader.getPos());

            nextOffset = offset.getLongValue();
            rowNumber.setValue(0);
        } catch (IOException e) {
            throw HyracksDataException.create(e);
        }
    }

    @Override
    public void index(ArrayTupleBuilder tb) throws HyracksDataException {
        try {
            if (recordReader.getPos() != nextOffset) {
                // start of a new group
                offset.setValue(nextOffset);
                nextOffset = recordReader.getPos();
                rowNumber.setValue(0);
            }
            tb.addField(intSerde, fileNumber);
            tb.addField(longSerde, offset);
            tb.addField(intSerde, rowNumber);
            rowNumber.setValue(rowNumber.getIntegerValue() + 1);
        } catch (IOException e) {
            throw HyracksDataException.create(e);
        }
    }

    @Override
    public int getNumberOfFields() {
        return NUM_OF_FIELDS;
    }

}
