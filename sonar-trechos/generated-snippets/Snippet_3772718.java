public interface IPrepareCommit {

    /**
     * Do prepare before commit
     */
    void prepareCommit(BatchId id, BasicOutputCollector collector) throws FailedException;
}
