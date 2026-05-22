public interface SSTableFlushObserver
{
    /**
     * Called before writing any data to the sstable.
     */
    void begin();

    /**
     * Called when a new partition in being written to the sstable,
     * but before any cells are processed (see {@link #nextUnfilteredCluster(Unfiltered)}).
     *
     * @param key The key being appended to SSTable.
     * @param indexPosition The position of the key in the SSTable PRIMARY_INDEX file.
     */
    void startPartition(DecoratedKey key, long indexPosition);

    /**
     * Called after the unfiltered cluster is written to the sstable.
     * Will be preceded by a call to {@code startPartition(DecoratedKey, long)},
     * and the cluster should be assumed to belong to that partition.
     *
     * @param unfilteredCluster The unfiltered cluster being added to SSTable.
     */
    void nextUnfilteredCluster(Unfiltered unfilteredCluster);

    /**
     * Called when all data is written to the file and it's ready to be finished up.
     */
    void complete();
}
