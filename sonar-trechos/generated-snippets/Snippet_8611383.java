public class PartitionCountersNeighborcastResponse extends GridCacheIdMessage {
    /** */
    private static final long serialVersionUID = -8731050539139260521L;

    /** */
    private IgniteUuid futId;

    /** */
    public PartitionCountersNeighborcastResponse() {
    }

    /** */
    public PartitionCountersNeighborcastResponse(IgniteUuid futId) {
        this.futId = futId;
    }

    /**
     * @return Sending future id.
     */
    public IgniteUuid futId() {
        return futId;
    }

    /** {@inheritDoc} */
    @Override public boolean writeTo(ByteBuffer buf, MessageWriter writer) {
        writer.setBuffer(buf);

        if (!super.writeTo(buf, writer))
            return false;

        if (!writer.isHeaderWritten()) {
            if (!writer.writeHeader(directType(), fieldsCount()))
                return false;

            writer.onHeaderWritten();
        }

        switch (writer.state()) {
            case 4:
                if (!writer.writeIgniteUuid("futId", futId))
                    return false;

                writer.incrementState();

        }

        return true;
    }

    /** {@inheritDoc} */
    @Override public boolean readFrom(ByteBuffer buf, MessageReader reader) {
        reader.setBuffer(buf);

        if (!reader.beforeMessageRead())
            return false;

        if (!super.readFrom(buf, reader))
            return false;

        switch (reader.state()) {
            case 4:
                futId = reader.readIgniteUuid("futId");

                if (!reader.isLastRead())
                    return false;

                reader.incrementState();

        }

        return reader.afterMessageRead(PartitionCountersNeighborcastResponse.class);
    }

    /** {@inheritDoc} */
    @Override public short directType() {
        return 166;
    }

    /** {@inheritDoc} */
    @Override public byte fieldsCount() {
        return 5;
    }

    /** {@inheritDoc} */
    @Override public boolean addDeploymentInfo() {
        return false;
    }
}
