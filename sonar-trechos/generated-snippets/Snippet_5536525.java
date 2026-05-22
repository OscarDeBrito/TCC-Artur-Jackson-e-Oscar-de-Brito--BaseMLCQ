public class Snippet__5536525 {

    private static final class EndOfStreamState {
        // set of upstream tasks
        private final Set<String> tasks = new HashSet<>();
        private final int expectedTotal;
        private volatile boolean isEndOfStream = false;

        EndOfStreamState(int expectedTotal) {
          this.expectedTotal = expectedTotal;
        }

        synchronized void update(String taskName) {
          if (taskName != null) {
            // aggregate the eos messages
            tasks.add(taskName);
            isEndOfStream = tasks.size() == expectedTotal;
          } else {
            // eos is coming from either source or aggregator task
            isEndOfStream = true;
          }
        }

        boolean isEndOfStream() {
          return isEndOfStream;
        }
      }

}
