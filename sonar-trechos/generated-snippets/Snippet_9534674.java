public class Snippet__9534674 {

    public boolean checkVolume(final HddsVolume volume, Callback callback) {
        if (volume == null) {
          LOG.debug("Cannot schedule check on null volume");
          return false;
        }

        Optional<ListenableFuture<VolumeCheckResult>> olf =
            delegateChecker.schedule(volume, null);
        if (olf.isPresent()) {
          numVolumeChecks.incrementAndGet();
          Futures.addCallback(olf.get(),
              new ResultHandler(volume, new HashSet<>(), new HashSet<>(),
                  new AtomicLong(1), callback),
              checkVolumeResultHandlerExecutorService
          );
          return true;
        }
        return false;
      }

}
