public class Snippet__8985872 {

    @Override
      public void append(PointValue pointValue) throws IOException {
        assert closed == false : "Point writer is already closed";
        BytesRef packedValue = pointValue.packedValue();
        assert packedValue.length == packedBytesLength  : "[packedValue] must have length [" + packedBytesLength + "] but was [" + packedValue.length + "]";
        out.writeBytes(packedValue.bytes, packedValue.offset, packedValue.length);
        BytesRef docIDBytes = pointValue.docIDBytes();
        assert docIDBytes.length == Integer.BYTES  : "[docIDBytes] must have length [" + Integer.BYTES + "] but was [" + docIDBytes.length + "]";
        out.writeBytes(docIDBytes.bytes, docIDBytes.offset, docIDBytes.length);
        count++;
        assert expectedCount == 0 || count <= expectedCount : "expectedCount=" + expectedCount + " vs count=" + count;
      }

}
