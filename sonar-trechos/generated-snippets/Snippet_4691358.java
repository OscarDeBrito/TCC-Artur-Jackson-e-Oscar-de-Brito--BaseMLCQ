public class Snippet__4691358 {

    @Override
        public void enterBarrier(BSPJobID jobId, TaskAttemptID taskId,
            long superstep) throws SyncException {
          try {
            barrier.await();
          } catch (Exception e) {
            throw new SyncException(e.toString());
          }
        }

}
