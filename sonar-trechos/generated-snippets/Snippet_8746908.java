public class RMContainerFinishedEvent extends RMContainerEvent {

  private final ContainerStatus remoteContainerStatus;

  public RMContainerFinishedEvent(ContainerId containerId,
      ContainerStatus containerStatus, RMContainerEventType event) {
    super(containerId, event);
    this.remoteContainerStatus = containerStatus;
  }

  public ContainerStatus getRemoteContainerStatus() {
    return this.remoteContainerStatus;
  }
}
