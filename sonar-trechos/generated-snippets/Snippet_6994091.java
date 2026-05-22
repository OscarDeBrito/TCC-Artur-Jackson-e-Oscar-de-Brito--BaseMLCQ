public class Snippet__6994091 {

    @Override
      protected String pendingToString() {
        ListenableFuture<? extends V> localInputFuture = delegateRef;
        ScheduledFuture<?> localTimer = timer;
        if (localInputFuture != null) {
          String message = "inputFuture=[" + localInputFuture + "]";
          if (localTimer != null) {
            final long delay = localTimer.getDelay(TimeUnit.MILLISECONDS);
            // Negative delays look confusing in an error message
            if (delay > 0) {
              message += ", remaining delay=[" + delay + " ms]";
            }
          }
          return message;
        }
        return null;
      }

}
