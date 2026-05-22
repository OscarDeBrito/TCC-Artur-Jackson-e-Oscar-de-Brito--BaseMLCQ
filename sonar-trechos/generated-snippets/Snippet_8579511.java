public class Snippet__8579511 {

    private static int putUint32(PositionedByteRange dst, int val) {
        dst.put((byte) (val >>> 24))
           .put((byte) (val >>> 16))
           .put((byte) (val >>> 8))
           .put((byte) val);
        return 4;
      }

}
