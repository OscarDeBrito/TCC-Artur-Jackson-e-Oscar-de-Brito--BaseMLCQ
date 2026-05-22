public class Snippet__7834065 {

    public abstract static class SystemTimerTask extends TimerTask {
        protected static final Logger logger = LogService.getLogger();

        /**
         * This is your executed action
         */
        public abstract void run2();

        /**
         * Does debug logging, catches critical errors, then delegates to {@link #run2()}
         */
        @Override
        public void run() {
          final boolean isDebugEnabled = logger.isTraceEnabled();
          if (isDebugEnabled) {
            logger.trace("SystemTimer.MyTask: starting {}", this);
          }
          try {
            this.run2();
          } catch (CancelException ignore) {
            // ignore: TimerThreads can fire during or near cache closure
          } catch (VirtualMachineError e) {
            SystemFailure.initiateFailure(e);
            throw e;
          } catch (Throwable t) {
            SystemFailure.checkFailure();
            logger.warn(String.format("Timer task <%s> encountered exception", this), t);
            // Don't rethrow, it will just get eaten and kill the timer
          }
          if (isDebugEnabled) {
            logger.trace("SystemTimer.MyTask: finished {}", this);
          }
        }
      }

}
