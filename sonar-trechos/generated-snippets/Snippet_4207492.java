public interface Indexer
    {
        /**
         * Notification of the start of a partition update.
         * This event always occurs before any other during the update.
         */
        public void begin();

        /**
         * Notification of a top level partition delete.
         * @param deletionTime
         */
        public void partitionDelete(DeletionTime deletionTime);

        /**
         * Notification of a RangeTombstone.
         * An update of a single partition may contain multiple RangeTombstones,
         * and a notification will be passed for each of them.
         * @param tombstone
         */
        public void rangeTombstone(RangeTombstone tombstone);

        /**
         * Notification that a new row was inserted into the Memtable holding the partition.
         * This only implies that the inserted row was not already present in the Memtable,
         * it *does not* guarantee that the row does not exist in an SSTable, potentially with
         * additional column data.
         *
         * @param row the Row being inserted into the base table's Memtable.
         */
        public void insertRow(Row row);

        /**
         * Notification of a modification to a row in the base table's Memtable.
         * This is allow an Index implementation to clean up entries for base data which is
         * never flushed to disk (and so will not be purged during compaction).
         * It's important to note that the old and new rows supplied here may not represent
         * the totality of the data for the Row with this particular Clustering. There may be
         * additional column data in SSTables which is not present in either the old or new row,
         * so implementations should be aware of that.
         * The supplied rows contain only column data which has actually been updated.
         * oldRowData contains only the columns which have been removed from the Row's
         * representation in the Memtable, while newRowData includes only new columns
         * which were not previously present. Any column data which is unchanged by
         * the update is not included.
         *
         * @param oldRowData data that was present in existing row and which has been removed from
         *                   the base table's Memtable
         * @param newRowData data that was not present in the existing row and is being inserted
         *                   into the base table's Memtable
         */
        public void updateRow(Row oldRowData, Row newRowData);

        /**
         * Notification that a row was removed from the partition.
         * Note that this is only called as part of either a compaction or a cleanup.
         * This context is indicated by the TransactionType supplied to the indexerFor method.
         *
         * As with updateRow, it cannot be guaranteed that all data belonging to the Clustering
         * of the supplied Row has been removed (although in the case of a cleanup, that is the
         * ultimate intention).
         * There may be data for the same row in other SSTables, so in this case Indexer implementations
         * should *not* assume that all traces of the row have been removed. In particular,
         * it is not safe to assert that all values associated with the Row's Clustering
         * have been deleted, so implementations which index primary key columns should not
         * purge those entries from their indexes.
         *
         * @param row data being removed from the base table
         */
        public void removeRow(Row row);

        /**
         * Notification of the end of the partition update.
         * This event always occurs after all others for the particular update.
         */
        public void finish();
    }
