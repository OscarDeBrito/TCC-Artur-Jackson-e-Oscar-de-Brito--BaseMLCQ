public class Snippet__4462987 {

    public Ack resumeFragment(final FragmentHandle handle) {
        // resume a pending fragment
        final FragmentManager manager = bee.getContext().getWorkBus().getFragmentManager(handle);
        if (manager != null) {
          manager.unpause();
          return Acks.OK;
        }

        // resume a paused fragment
        final FragmentExecutor runner = bee.getFragmentRunner(handle);
        if (runner != null) {
          runner.unpause();
          return Acks.OK;
        }

        // fragment completed or does not exist
        logger.warn("Dropping request to resume fragment. {} does not exist.", QueryIdHelper.getQueryIdentifier(handle));
        return Acks.OK;
      }

}
