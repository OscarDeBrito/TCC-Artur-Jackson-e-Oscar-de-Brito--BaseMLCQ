public class Snippet__9517356 {

    private void writeRawBytes(Writable writable) throws IOException {
        if (writable instanceof BytesWritable) {
          BytesWritable bw = (BytesWritable) writable;
          byte[] bytes = bw.getBytes();
          int length = bw.getLength();
          clientOut.writeInt(length);
          clientOut.write(bytes, 0, length);
        } else {
          bufferOut.reset();
          writable.write(bufferDataOut);
          byte[] bytes = bufferOut.toByteArray();
          clientOut.writeInt(bytes.length);
          clientOut.write(bytes);
        }
      }

}
