public class Snippet__9022124 {

    @Override
        public void finish() throws IOException {
          if (logDebug) { log.debug("PRE_UPDATE FINISH " + req); }
          if (next != null) next.finish();

          // LOG A SUMMARY WHEN ALL DONE (INFO LEVEL)

          if (log.isInfoEnabled()) {
            log.info(getLogStringAndClearRspToLog());
          }

          if (log.isWarnEnabled() && slowUpdateThresholdMillis >= 0) {
            final long elapsed = (long) req.getRequestTimer().getTime();
            if (elapsed >= slowUpdateThresholdMillis) {
              log.warn("slow: " + getLogStringAndClearRspToLog());
            }
          }
        }

}
