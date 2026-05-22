public class TezProgressMonitorStatusMapper implements ProgressMonitorStatusMapper {

  /**
   * These states are taken form DAGStatus.State, could not use that here directly as it was
   * optional dependency and did not want to include it just for the enum.
   */
  enum TezStatus {
    SUBMITTED, INITING, RUNNING, SUCCEEDED, KILLED, FAILED, ERROR

  }

  @Override
  public TJobExecutionStatus forStatus(String status) {
    if (StringUtils.isEmpty(status)) {
      return TJobExecutionStatus.NOT_AVAILABLE;
    }
      TezStatus tezStatus = TezStatus.valueOf(status);
      switch (tezStatus) {
      case SUBMITTED:
      case INITING:
      case RUNNING:
        return TJobExecutionStatus.IN_PROGRESS;
      default:
        return TJobExecutionStatus.COMPLETE;
      }
  }
}
