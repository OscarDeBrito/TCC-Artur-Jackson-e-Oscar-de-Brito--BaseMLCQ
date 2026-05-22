public class StandaloneCheckpointRecoveryFactory implements CheckpointRecoveryFactory {

	@Override
	public CompletedCheckpointStore createCheckpointStore(JobID jobId, int maxNumberOfCheckpointsToRetain, ClassLoader userClassLoader)
			throws Exception {

		return new StandaloneCompletedCheckpointStore(maxNumberOfCheckpointsToRetain);
	}

	@Override
	public CheckpointIDCounter createCheckpointIDCounter(JobID ignored) {
		return new StandaloneCheckpointIDCounter();
	}

}
