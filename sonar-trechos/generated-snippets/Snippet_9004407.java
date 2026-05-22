public class Snippet__9004407 {

    @Override
        public BytesRef next() {
          if (input.position() < end) {
            try {
              int code = input.readVInt();
              boolean newField = (code & 1) != 0;
              if (newField) {
                field = input.readString();
              }
              int prefix = code >>> 1;
              int suffix = input.readVInt();
              readTermBytes(prefix, suffix);
              return bytes;
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          } else {
            field = null;
            return null;
          }
        }

}
