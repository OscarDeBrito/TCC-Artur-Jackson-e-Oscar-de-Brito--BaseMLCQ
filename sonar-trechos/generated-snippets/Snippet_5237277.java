public class Snippet__5237277 {

    static void copy(final InputStream input, final OutputStream output) {
        if (output == null || input == null) {
          return;
        }

        try {
          ByteBuffer inBuffer = ByteBuffer.allocate(8192);
          ReadableByteChannel ic = Channels.newChannel(input);
          WritableByteChannel oc = Channels.newChannel(output);
          while (ic.read(inBuffer) > 0) {
            inBuffer.flip();
            oc.write(inBuffer);
            inBuffer.rewind();
          }
        } catch (IOException e) {
          throw new ODataRuntimeException("Error on reading request content");
        } finally {
          closeStream(input);
          closeStream(output);
        }
      }

}
