public class ContainersMonitorEvent extends
    AbstractEvent<ContainersMonitorEventType> {

  private final ContainerId containerId;

  public ContainersMonitorEvent(ContainerId containerId,
      ContainersMonitorEventType eventType) {
    super(eventType);
    this.containerId = containerId;
  }

  public ContainerId getContainerId() {
    return this.containerId;
  }

}
