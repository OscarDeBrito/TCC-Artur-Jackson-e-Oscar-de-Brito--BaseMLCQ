public class Snippet__5114470 {

    protected abstract static class HistoryRecord
      {
        protected final long timestamp;
        protected final Exception trace;
        protected final String threadName;
    
        public HistoryRecord(Exception trace, long timestamp, String threadName)
        {
          this.trace = trace;
          this.timestamp = timestamp;
          this.threadName = threadName;
        }
    
        public void print(String description)
        {
          Logging.diagnostics.debug("== "+description+" by '"+threadName+"' at "+new Long(timestamp)+" ==",trace);
        }
    
        public boolean isFlushable(long timestamp)
        {
          return this.timestamp < timestamp;
        }

        public abstract boolean applies(Long recordID);
    
        public abstract void print();

      }

}
