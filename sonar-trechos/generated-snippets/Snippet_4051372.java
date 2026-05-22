public class Snippet__4051372 {

    public boolean hasFailed() throws ExecutionException, InterruptedException {
        if (processBundleResponse != null && processBundleResponse.toCompletableFuture().isDone()) {
          return !processBundleResponse.toCompletableFuture().get().getError().isEmpty();
        } else {
          // At the very least, we don't know that this has failed yet.
          return false;
        }
      }

}
