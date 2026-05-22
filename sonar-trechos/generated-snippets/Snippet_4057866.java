public class Snippet__4057866 {

    protected void readPayload(ByteBuf in, boolean copyData) throws IOException {
            int length = in.readInt();
            if (length < 0) {
                throw new EOFException("Log Record is corrupt: Negative length " + length);
            }
            if (copyData) {
                setPayloadBuf(in.slice(in.readerIndex(), length), true);
            } else {
                setPayloadBuf(in.retainedSlice(in.readerIndex(), length), false);
            }
            in.skipBytes(length);
        }

}
