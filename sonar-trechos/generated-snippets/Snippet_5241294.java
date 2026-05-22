public class Snippet__5241294 {

    private static void closeStream(final Channel closeable) {
        if (closeable != null) {
          try {
            closeable.close();
          } catch (IOException e) {
            // ignore
          }
        }
      }

}
