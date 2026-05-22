public class CpuUsageDistributionGoal extends ResourceDistributionGoal {

  /**
   * Constructor for Resource Distribution Goal.
   */
  public CpuUsageDistributionGoal() {
    super();
  }

  /**
   * Package private for unit test.
   */
  CpuUsageDistributionGoal(BalancingConstraint constraint) {
    super(constraint);
  }

  @Override
  protected Resource resource() {
    return Resource.CPU;
  }

  @Override
  public String name() {
    return CpuUsageDistributionGoal.class.getSimpleName();
  }
}
