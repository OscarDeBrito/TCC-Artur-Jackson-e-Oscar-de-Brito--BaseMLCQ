public class Snippet__4743999 {

    public static Protos.FrameworkID toFrameworkID(ByteBuffer bb) {
        try {
          return Protos.FrameworkID.parseFrom(getBytes(bb, bb.getInt()));
        } catch (Exception e) {
          throw new RuntimeException("Failed to parse Framework ID", e);
        }
      }

}
