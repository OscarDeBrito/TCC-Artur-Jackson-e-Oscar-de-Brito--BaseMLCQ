public class SerializableGlobalSumAggregateFunction extends AbstractSerializableSumAggregateFunction {

    public SerializableGlobalSumAggregateFunction(IScalarEvaluatorFactory[] args, IHyracksTaskContext context,
            SourceLocation sourceLoc) throws HyracksDataException {
        super(args, context, sourceLoc);
    }

    // Called for each incoming tuple
    @Override
    public void step(IFrameTupleReference tuple, byte[] state, int start, int len) throws HyracksDataException {
        super.step(tuple, state, start, len);
    }

    // Finish calculation
    @Override
    public void finish(byte[] state, int start, int len, DataOutput out) throws HyracksDataException {
        super.finish(state, start, len, out);
    }

    // Is skip
    @Override
    protected boolean skipStep(byte[] state, int start) {
        ATypeTag aggType = EnumDeserializer.ATYPETAGDESERIALIZER.deserialize(state[start + AGG_TYPE_OFFSET]);
        return aggType == ATypeTag.NULL;
    }

    // Handle NULL step
    @Override
    protected void processNull(byte[] state, int start) {
        state[start + AGG_TYPE_OFFSET] = ATypeTag.SERIALIZED_NULL_TYPE_TAG;
    }

    // Handle SYSTEM_NULL step
    @Override
    protected void processSystemNull() {
        // Do nothing
    }

    // Handle NULL finish
    @Override
    protected void finishNull(DataOutput out) throws IOException {
        out.writeByte(ATypeTag.SERIALIZED_NULL_TYPE_TAG);
    }

    // Handle SYSTEM_NULL finish
    @Override
    protected void finishSystemNull(DataOutput out) throws IOException {
        out.writeByte(ATypeTag.SERIALIZED_NULL_TYPE_TAG);
    }
}
