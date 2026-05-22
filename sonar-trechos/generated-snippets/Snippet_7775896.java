public class Snippet__7775896 {

    synchronized void removeSegment(DataSegment segment)
      {
        if (segments.remove(segment)) {
          currSize -= segment.getSize();
        }
      }

}
