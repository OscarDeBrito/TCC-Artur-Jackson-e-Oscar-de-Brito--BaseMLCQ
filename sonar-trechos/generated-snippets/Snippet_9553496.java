public class NMNullStateStoreService extends NMStateStoreService {

  public NMNullStateStoreService() {
    super(NMNullStateStoreService.class.getName());
  }

  @Override
  public boolean canRecover() {
    return false;
  }

  @Override
  public RecoveredApplicationsState loadApplicationsState() throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeApplication(ApplicationId appId,
      ContainerManagerApplicationProto p) throws IOException {
  }

  @Override
  public void removeApplication(ApplicationId appId) throws IOException {
  }

  @Override
  public RecoveryIterator<RecoveredContainerState> getContainerStateIterator()
      throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeContainer(ContainerId containerId, int version,
      long startTime, StartContainerRequest startRequest) {
  }

  @Override
  public void storeContainerQueued(ContainerId containerId) throws IOException {
  }

  @Override
  public void storeContainerPaused(ContainerId containerId) throws IOException {
  }

  @Override
  public void removeContainerPaused(ContainerId containerId)
      throws IOException {
  }

  @Override
  public void storeContainerDiagnostics(ContainerId containerId,
      StringBuilder diagnostics) throws IOException {
  }

  @Override
  public void storeContainerLaunched(ContainerId containerId)
      throws IOException {
  }

  @Override
  public void storeContainerUpdateToken(ContainerId containerId,
      ContainerTokenIdentifier containerTokenIdentifier) throws IOException {
  }

  @Override
  public void storeContainerKilled(ContainerId containerId)
      throws IOException {
  }

  @Override
  public void storeContainerCompleted(ContainerId containerId, int exitCode)
      throws IOException {
  }

  @Override
  public void storeContainerRemainingRetryAttempts(ContainerId containerId,
      int remainingRetryAttempts) throws IOException {
  }

  @Override
  public void storeContainerRestartTimes(ContainerId containerId,
      List<Long> restartTimes) throws IOException {
  }

  @Override
  public void storeContainerWorkDir(ContainerId containerId,
      String workDir) throws IOException {
  }

  @Override
  public void storeContainerLogDir(ContainerId containerId,
      String logDir) throws IOException {
  }

  @Override
  public void removeContainer(ContainerId containerId) throws IOException {
  }

  @Override
  public RecoveredLocalizationState loadLocalizationState()
      throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void startResourceLocalization(String user, ApplicationId appId,
      LocalResourceProto proto, Path localPath) throws IOException {
  }

  @Override
  public void finishResourceLocalization(String user, ApplicationId appId,
      LocalizedResourceProto proto) throws IOException {
  }

  @Override
  public void removeLocalizedResource(String user, ApplicationId appId,
      Path localPath) throws IOException {
  }

  @Override
  public RecoveredDeletionServiceState loadDeletionServiceState()
      throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeDeletionTask(int taskId,
      DeletionServiceDeleteTaskProto taskProto) throws IOException {
  }

  @Override
  public void removeDeletionTask(int taskId) throws IOException {
  }

  @Override
  public RecoveredNMTokensState loadNMTokensState() throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeNMTokenCurrentMasterKey(MasterKey key)
      throws IOException {
  }

  @Override
  public void storeNMTokenPreviousMasterKey(MasterKey key)
      throws IOException {
  }

  @Override
  public void storeNMTokenApplicationMasterKey(ApplicationAttemptId attempt,
      MasterKey key) throws IOException {
  }

  @Override
  public void removeNMTokenApplicationMasterKey(ApplicationAttemptId attempt)
      throws IOException {
  }

  @Override
  public RecoveredContainerTokensState loadContainerTokensState()
      throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeContainerTokenCurrentMasterKey(MasterKey key)
      throws IOException {
  }

  @Override
  public void storeContainerTokenPreviousMasterKey(MasterKey key)
      throws IOException {
  }

  @Override
  public void storeContainerToken(ContainerId containerId,
      Long expirationTime) throws IOException {
  }

  @Override
  public void removeContainerToken(ContainerId containerId)
      throws IOException {
  }

  @Override
  public RecoveredLogDeleterState loadLogDeleterState() throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeLogDeleter(ApplicationId appId, LogDeleterProto proto)
      throws IOException {
  }

  @Override
  public void removeLogDeleter(ApplicationId appId) throws IOException {
  }

  @Override
  public RecoveredAMRMProxyState loadAMRMProxyState() throws IOException {
    throw new UnsupportedOperationException(
        "Recovery not supported by this state store");
  }

  @Override
  public void storeAMRMProxyCurrentMasterKey(MasterKey key) throws IOException {
  }

  @Override
  public void storeAMRMProxyNextMasterKey(MasterKey key) throws IOException {
  }

  @Override
  public void storeAMRMProxyAppContextEntry(ApplicationAttemptId attempt,
      String key, byte[] data) throws IOException {
  }

  @Override
  public void removeAMRMProxyAppContextEntry(ApplicationAttemptId attempt,
      String key) throws IOException {
  }

  @Override
  public void removeAMRMProxyAppContext(ApplicationAttemptId attempt)
      throws IOException {
  }

  @Override
  public void storeAssignedResources(Container container,
      String resourceType, List<Serializable> assignedResources)
      throws IOException {
    updateContainerResourceMapping(container, resourceType, assignedResources);
  }

  @Override
  protected void initStorage(Configuration conf) throws IOException {
  }

  @Override
  protected void startStorage() throws IOException {
  }

  @Override
  protected void closeStorage() throws IOException {
  }
}
