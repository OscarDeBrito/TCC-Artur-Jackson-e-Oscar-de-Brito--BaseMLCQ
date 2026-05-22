public abstract class AbstractBatchingLogger implements BatchingLogger {

  public static final int DEFAULT_MIN_BATCH_SIZE = 1024 * 128; // This is pretty arbitrary.

  protected static class BatchEntry {
    private final String line;

    public BatchEntry(String line) {
      this.line = line;
    }

    public String getLine() {
      return line;
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof BatchEntry)) {
        return false;
      }

      BatchEntry that = (BatchEntry) other;
      return line.equals(that.line);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(line);
    }

    @Override
    public String toString() {
      return String.format("BatchEntry(%s)", line);
    }
  }

  private ImmutableList.Builder<BatchEntry> batch;
  private int currentBatchSize;
  private final int minBatchSize;

  public AbstractBatchingLogger(int minBatchSize) {
    this.batch = ImmutableList.builder();
    this.currentBatchSize = 0;
    this.minBatchSize = minBatchSize;
  }

  public AbstractBatchingLogger() {
    this(DEFAULT_MIN_BATCH_SIZE);
  }

  @Override
  public Optional<ListenableFuture<Void>> log(String logLine) {
    batch.add(new BatchEntry(logLine));
    currentBatchSize += logLine.length();
    if (currentBatchSize >= minBatchSize) {
      return Optional.of(sendBatch());
    }
    return Optional.empty();
  }

  @Override
  public ListenableFuture<Void> forceFlush() {
    return sendBatch();
  }

  private ListenableFuture<Void> sendBatch() {
    ImmutableList<BatchEntry> toSend = batch.build();
    batch = ImmutableList.builder();
    currentBatchSize = 0;
    if (toSend.isEmpty()) {
      return Futures.immediateFuture(null);
    } else {
      return logMultiple(toSend);
    }
  }

  protected abstract ListenableFuture<Void> logMultiple(ImmutableCollection<BatchEntry> data);
}
