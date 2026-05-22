public class Snippet__5534322 {

    public Snippet__5534322(SystemStreamPartition ssp, Counter eventReadRate, Counter eventByteReadRate,
            SamzaHistogram readLatency, Counter readErrors, Interceptor interceptor, int maxEventCount) {
          this.ssp = ssp;
          this.eventReadRate = eventReadRate;
          this.eventByteReadRate = eventByteReadRate;
          this.readLatency = readLatency;
          this.errorRate = readErrors;
          this.interceptor = interceptor;
          this.maxEventCount = maxEventCount;
        }

}
