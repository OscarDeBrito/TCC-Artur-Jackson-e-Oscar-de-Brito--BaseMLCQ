public class Snippet__7094545 {

    @Override
        public ByteBuffer put(byte x) {
            if (isReadOnly) {
                throw new ReadOnlyBufferException();
            }
            hb[ix(nextPutIndex())] = x;
            return this;
        }

}
