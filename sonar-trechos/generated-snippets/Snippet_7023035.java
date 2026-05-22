public class Snippet__7023035 {

    public Snippet__7023035(final Operation operation, final long operations, final long bytes, final long latencies,
                            final Map<Integer, Long> statusCodes, long timestampStart, long timestampFinish) {
        this.operation = operation;
        this.operations = operations;
        this.bytes = bytes;
        this.latencies = latencies;
        this.statusCodes = ImmutableSortedMap.copyOf(statusCodes);
        if (this.operations > 0) {
          double average = (double) this.latencies / this.operations;
          this.averageLatency = Math.round(average * 100.00) / 100.00;
        }
        this.runtime = ((double) (timestampFinish - timestampStart)) / TimeUnit.SECONDS.toMillis(1);
      }

}
