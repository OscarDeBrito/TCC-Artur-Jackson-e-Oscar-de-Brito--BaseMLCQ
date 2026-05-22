public class ZooKeeperMesosServices extends AbstractMesosServices {

	// Factory to create ZooKeeper utility classes
	private final ZooKeeperUtilityFactory zooKeeperUtilityFactory;

	public ZooKeeperMesosServices(ActorSystem actorSystem, MesosArtifactServer artifactServer, ZooKeeperUtilityFactory zooKeeperUtilityFactory) {
		super(actorSystem, artifactServer);
		this.zooKeeperUtilityFactory = Preconditions.checkNotNull(zooKeeperUtilityFactory);
	}

	@Override
	public MesosWorkerStore createMesosWorkerStore(Configuration configuration, Executor executor) throws Exception {
		RetrievableStateStorageHelper<MesosWorkerStore.Worker> stateStorageHelper =
			ZooKeeperUtils.createFileSystemStateStorage(configuration, "mesosWorkerStore");

		ZooKeeperStateHandleStore<MesosWorkerStore.Worker> zooKeeperStateHandleStore = zooKeeperUtilityFactory.createZooKeeperStateHandleStore(
			"/workers",
			stateStorageHelper);

		ZooKeeperSharedValue frameworkId = zooKeeperUtilityFactory.createSharedValue("/frameworkId", new byte[0]);
		ZooKeeperSharedCount totalTaskCount = zooKeeperUtilityFactory.createSharedCount("/taskCount", 0);

		return new ZooKeeperMesosWorkerStore(
			zooKeeperStateHandleStore,
			frameworkId,
			totalTaskCount);
	}

	@Override
	public void close(boolean cleanup) throws Exception {
		Throwable exception = null;

		try {
			// this also closes the underlying CuratorFramework instance
			zooKeeperUtilityFactory.close(cleanup);
		} catch (Throwable t) {
			exception = ExceptionUtils.firstOrSuppressed(t, exception);
		}

		try {
			super.close(cleanup);
		} catch (Throwable t) {
			exception = ExceptionUtils.firstOrSuppressed(t, exception);
		}

		if (exception != null) {
			throw new FlinkException("Could not properly shut down the Mesos services.", exception);
		}
	}
}
