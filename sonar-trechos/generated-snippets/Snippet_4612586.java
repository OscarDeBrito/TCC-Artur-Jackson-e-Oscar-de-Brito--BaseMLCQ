public class CheckpointStatsSnapshot implements Serializable {

	private static final long serialVersionUID = 8914278419087217964L;

	/** Snapshot of the checkpoint counts. */
	private final CheckpointStatsCounts counts;

	/** Snapshot of the completed checkpoints summary stats. */
	private final CompletedCheckpointStatsSummary summary;

	/** Snapshot of the checkpoint history. */
	private final CheckpointStatsHistory history;

	/** The latest restored checkpoint operation. */
	@Nullable
	private final RestoredCheckpointStats latestRestoredCheckpoint;

	/**
	 * Creates a stats snapshot.
	 *
	 * @param counts Snapshot of the checkpoint counts.
	 * @param summary Snapshot of the completed checkpoints summary stats.
	 * @param history Snapshot of the checkpoint history.
	 * @param latestRestoredCheckpoint The latest restored checkpoint operation.
	 */
	CheckpointStatsSnapshot(
			CheckpointStatsCounts counts,
			CompletedCheckpointStatsSummary summary,
			CheckpointStatsHistory history,
			@Nullable RestoredCheckpointStats latestRestoredCheckpoint) {

		this.counts = checkNotNull(counts);
		this.summary= checkNotNull(summary);
		this.history = checkNotNull(history);
		this.latestRestoredCheckpoint = latestRestoredCheckpoint;
	}

	/**
	 * Returns the snapshotted checkpoint counts.
	 *
	 * @return Snapshotted checkpoint counts.
	 */
	public CheckpointStatsCounts getCounts() {
		return counts;
	}

	/**
	 * Returns the snapshotted completed checkpoint summary stats.
	 *
	 * @return Snapshotted completed checkpoint summary stats.
	 */
	public CompletedCheckpointStatsSummary getSummaryStats() {
		return summary;
	}

	/**
	 * Returns the snapshotted checkpoint history.
	 *
	 * @return Snapshotted checkpoint history.
	 */
	public CheckpointStatsHistory getHistory() {
		return history;
	}

	/**
	 * Returns the latest restored checkpoint.
	 *
	 * @return Latest restored checkpoint or <code>null</code>.
	 */
	@Nullable
	public RestoredCheckpointStats getLatestRestoredCheckpoint() {
		return latestRestoredCheckpoint;
	}
}
