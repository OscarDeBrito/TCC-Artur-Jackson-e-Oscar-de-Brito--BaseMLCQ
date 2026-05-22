public class Snippet__8978631 {

    protected void markLargestWriterPending(DocumentsWriterFlushControl control,
          ThreadState perThreadState, final long currentBytesPerThread) {
        ThreadState largestNonPendingWriter = findLargestNonPendingWriter(control, perThreadState);
        if (largestNonPendingWriter != null) {
          control.setFlushPending(largestNonPendingWriter);
        }
      }

}
