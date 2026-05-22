public class Snippet__4688226 {

    @Override
      public void writeByte(int v) throws IOException {
        ensureSize(ByteUtils.SIZE_OF_BYTE);
        UNSAFE.putByte(buf, BYTE_ARRAY_OFFSET + pos, (byte) v);
        pos += ByteUtils.SIZE_OF_BYTE;
      }

}
