public class DnsMetrics {

  /** Disposition of a publish request. */
  public enum PublishStatus { ACCEPTED, REJECTED }

  /** Disposition of writer.commit(). */
  public enum CommitStatus { SUCCESS, FAILURE }

  /** Disposition of the publish action. */
  public enum ActionStatus { SUCCESS, COMMIT_FAILURE, LOCK_FAILURE, BAD_WRITER, BAD_LOCK_INDEX }

  private static final ImmutableSet<LabelDescriptor> LABEL_DESCRIPTORS_FOR_PUBLISH_REQUESTS =
      ImmutableSet.of(
          LabelDescriptor.create("tld", "TLD"),
          LabelDescriptor.create(
              "status", "Whether the publish request was accepted or rejected."));

  private static final ImmutableSet<LabelDescriptor> LABEL_DESCRIPTORS_FOR_COMMIT =
      ImmutableSet.of(
          LabelDescriptor.create("tld", "TLD"),
          LabelDescriptor.create("status", "Whether writer.commit() succeeded or failed."),
          LabelDescriptor.create("dnsWriter", "The DnsWriter used."));

  private static final ImmutableSet<LabelDescriptor> LABEL_DESCRIPTORS_FOR_LATENCY =
      ImmutableSet.of(
          LabelDescriptor.create("tld", "TLD"),
          LabelDescriptor.create("status", "Whether the publish succeeded, or why it failed."),
          LabelDescriptor.create("dnsWriter", "The DnsWriter used."));

  // Finer-grained fitter than the DEFAULT_FITTER, allows values between 100 ms and just over 29
  // hours.
  private static final DistributionFitter EXPONENTIAL_FITTER =
      ExponentialFitter.create(20, 2.0, 100.0);

  // Fibonacci fitter more suitible for integer-type values. Allows values between 0 and 10946,
  // which is the 21th Fibonacci number.
  private static final DistributionFitter FIBONACCI_FITTER =
      FibonacciFitter.create(10946);

  private static final IncrementableMetric publishDomainRequests =
      MetricRegistryImpl.getDefault()
          .newIncrementableMetric(
              "/dns/publish_domain_requests",
              "count of publishDomain requests",
              "count",
              LABEL_DESCRIPTORS_FOR_PUBLISH_REQUESTS);

  private static final IncrementableMetric publishHostRequests =
      MetricRegistryImpl.getDefault()
          .newIncrementableMetric(
              "/dns/publish_host_requests",
              "count of publishHost requests",
              "count",
              LABEL_DESCRIPTORS_FOR_PUBLISH_REQUESTS);

  private static final IncrementableMetric commitCount =
      MetricRegistryImpl.getDefault()
          .newIncrementableMetric(
              "/dns/commit_requests",
              "Count of writer.commit() calls",
              "count",
              LABEL_DESCRIPTORS_FOR_COMMIT);

  private static final IncrementableMetric domainsCommittedCount =
      MetricRegistryImpl.getDefault()
          .newIncrementableMetric(
              "/dns/domains_committed",
              "Count of domains committed",
              "count",
              LABEL_DESCRIPTORS_FOR_COMMIT);

  private static final IncrementableMetric hostsCommittedCount =
      MetricRegistryImpl.getDefault()
          .newIncrementableMetric(
              "/dns/hosts_committed",
              "Count of hosts committed",
              "count",
              LABEL_DESCRIPTORS_FOR_COMMIT);

  private static final EventMetric processingTimePerCommitDist =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/per_batch/processing_time",
              "publishDnsUpdates Processing Time",
              "milliseconds",
              LABEL_DESCRIPTORS_FOR_COMMIT,
              EXPONENTIAL_FITTER);

  private static final EventMetric normalizedProcessingTimePerCommitDist =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/per_batch/processing_time_per_dns_update",
              "publishDnsUpdates Processing Time, divided by the batch size",
              "milliseconds",
              LABEL_DESCRIPTORS_FOR_COMMIT,
              EXPONENTIAL_FITTER);

  private static final EventMetric totalBatchSizePerCommitDist =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/per_batch/batch_size",
              "Number of hosts and domains committed in each publishDnsUpdates",
              "count",
              LABEL_DESCRIPTORS_FOR_COMMIT,
              FIBONACCI_FITTER);

  private static final EventMetric processingTimePerItemDist =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/per_item/processing_time",
              "publishDnsUpdates Processing Time",
              "milliseconds",
              LABEL_DESCRIPTORS_FOR_COMMIT,
              EXPONENTIAL_FITTER);

  private static final EventMetric normalizedProcessingTimePerItemDist =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/per_item/processing_time_per_dns_update",
              "publishDnsUpdates Processing Time, divided by the batch size",
              "milliseconds",
              LABEL_DESCRIPTORS_FOR_COMMIT,
              EXPONENTIAL_FITTER);

  private static final EventMetric totalBatchSizePerItemDist =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/per_item/batch_size",
              "Batch sizes for hosts and domains",
              "count",
              LABEL_DESCRIPTORS_FOR_COMMIT,
              FIBONACCI_FITTER);

  private static final EventMetric updateRequestLatency =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/update_latency",
              "Time elapsed since refresh request was created until it was published",
              "milliseconds",
              LABEL_DESCRIPTORS_FOR_LATENCY,
              EXPONENTIAL_FITTER);

  private static final EventMetric publishQueueDelay =
      MetricRegistryImpl.getDefault()
          .newEventMetric(
              "/dns/publish_queue_delay",
              "Time elapsed since the publishDnsUpdates action was created until it was executed",
              "milliseconds",
              LABEL_DESCRIPTORS_FOR_LATENCY,
              EXPONENTIAL_FITTER);

  @Inject RegistryEnvironment registryEnvironment;

  @Inject
  DnsMetrics() {}

  /**
   * Increment a monotonic counter that tracks calls to {@link
   * google.registry.dns.writer.DnsWriter#publishDomain(String)}, per TLD.
   */
  public void incrementPublishDomainRequests(String tld, long numRequests, PublishStatus status) {
    if (numRequests > 0) {
      publishDomainRequests.incrementBy(numRequests, tld, status.name());
    }
  }

  /**
   * Increment a monotonic counter that tracks calls to {@link
   * google.registry.dns.writer.DnsWriter#publishHost(String)}, per TLD.
   */
  public void incrementPublishHostRequests(String tld, long numRequests, PublishStatus status) {
    if (numRequests > 0) {
      publishHostRequests.incrementBy(numRequests, tld, status.name());
    }
  }

  /**
   * Measures information about the entire batched commit, per TLD.
   *
   * <p>The information includes running times (per item and per commit), and batch sizes (per item
   * and per commit)
   *
   * <p>This is to be used for load testing the system, and will not measure anything in prod.
   */
  void recordCommit(
      String tld,
      String dnsWriter,
      CommitStatus status,
      Duration processingDuration,
      int numberOfDomains,
      int numberOfHosts) {
    // We don't want to record all these metrics in production, as they are quite expensive
    if (registryEnvironment == RegistryEnvironment.PRODUCTION) {
      return;
    }
    int batchSize = numberOfDomains + numberOfHosts;

    processingTimePerCommitDist.record(
        processingDuration.getMillis(), tld, status.name(), dnsWriter);
    processingTimePerItemDist.record(
        processingDuration.getMillis(), batchSize, tld, status.name(), dnsWriter);

    if (batchSize > 0) {
      normalizedProcessingTimePerCommitDist.record(
          (double) processingDuration.getMillis() / batchSize,
          tld, status.name(), dnsWriter);
      normalizedProcessingTimePerItemDist.record(
          (double) processingDuration.getMillis() / batchSize,
          batchSize,
          tld, status.name(), dnsWriter);
    }

    totalBatchSizePerCommitDist.record(batchSize, tld, status.name(), dnsWriter);

    totalBatchSizePerItemDist.record(batchSize, batchSize, tld, status.name(), dnsWriter);

    commitCount.increment(tld, status.name(), dnsWriter);
    domainsCommittedCount.incrementBy(numberOfDomains, tld, status.name(), dnsWriter);
    hostsCommittedCount.incrementBy(numberOfHosts, tld, status.name(), dnsWriter);
  }

  void recordActionResult(
      String tld,
      String dnsWriter,
      ActionStatus status,
      int numberOfItems,
      Duration timeSinceUpdateRequest,
      Duration timeSinceActionEnqueued) {
    updateRequestLatency.record(
        timeSinceUpdateRequest.getMillis(), numberOfItems, tld, status.name(), dnsWriter);
    publishQueueDelay.record(timeSinceActionEnqueued.getMillis(), tld, status.name(), dnsWriter);
  }
}
