static class ReadRequestCostFunction extends CostFromRegionLoadAsRateFunction {

    private static final String READ_REQUEST_COST_KEY =
        "hbase.master.balancer.stochastic.readRequestCost";
    private static final float DEFAULT_READ_REQUEST_COST = 5;

    ReadRequestCostFunction(Configuration conf) {
      super(conf);
      this.setMultiplier(conf.getFloat(READ_REQUEST_COST_KEY, DEFAULT_READ_REQUEST_COST));
    }

    @Override
    protected double getCostFromRl(BalancerRegionLoad rl) {
      return rl.getReadRequestsCount();
    }
  }
