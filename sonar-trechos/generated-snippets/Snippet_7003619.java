@Parameters(separators = " =")
final class BigqueryParameters {

  /**
   * Default to 20 threads to stay within Bigquery's rate limit of 20 concurrent queries.
   *
   * @see <a href="https://cloud.google.com/bigquery/quota-policy">BigQuery Quota Policy</a>
   */
  private static final int DEFAULT_NUM_THREADS = 20;

  @Parameter(
      names = "--bigquery_dataset",
      description = "Name of the default dataset to use, for reading and writing.")
  private String bigqueryDataset = BigqueryConnection.DEFAULT_DATASET_NAME;

  @Parameter(
      names = "--bigquery_overwrite",
      description = "Whether to automatically overwrite existing tables and views.")
  private boolean bigqueryOverwrite;

  @Parameter(
      names = "--bigquery_poll_interval",
      description = "Interval in milliseconds to wait between polls for job status.")
  private Duration bigqueryPollInterval = Duration.standardSeconds(1);

  @Parameter(
      names = "--bigquery_num_threads",
      description = "Number of threads for running simultaneous BigQuery operations.")
  private int bigqueryNumThreads = DEFAULT_NUM_THREADS;

  /** Returns a new BigqueryConnection constructed according to the delegate's flag settings. */
  BigqueryConnection newConnection(BigqueryConnection.Builder connectionBuilder) throws Exception {
    BigqueryConnection connection =
        connectionBuilder
            .setExecutorService(Executors.newFixedThreadPool(bigqueryNumThreads))
            .setDatasetId(bigqueryDataset)
            .setOverwrite(bigqueryOverwrite)
            .setPollInterval(bigqueryPollInterval)
            .build();
    return connection;
  }
}
