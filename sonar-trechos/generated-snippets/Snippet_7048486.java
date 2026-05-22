private class DiskDistributionGoalStatsComparator implements ClusterModelStatsComparator {
    private String _reasonForLastNegativeResult;

    @Override
    public int compare(ClusterModelStats stats1, ClusterModelStats stats2) {
      // Number of balanced brokers in the highest priority resource cannot be more than the pre-optimized
      // stats. This constraint is applicable for the rest of the resources, if their higher priority resources
      // have the same number of balanced brokers in their corresponding pre- and post-optimized stats.
      int numBalancedBroker1 = stats1.numBalancedBrokersByResource().get(DISK);
      int numBalancedBroker2 = stats2.numBalancedBrokersByResource().get(DISK);
      // First compare the
      if (numBalancedBroker2 > numBalancedBroker1) {
        _reasonForLastNegativeResult = String.format(
            "Violated %s. [Number of Balanced Brokers] for resource %s. post-optimization:%d pre-optimization:%d",
            name(), DISK, numBalancedBroker1, numBalancedBroker2);
        return -1;
      }
      return 1;
    }

    @Override
    public String explainLastComparison() {
      return _reasonForLastNegativeResult;
    }
  }
