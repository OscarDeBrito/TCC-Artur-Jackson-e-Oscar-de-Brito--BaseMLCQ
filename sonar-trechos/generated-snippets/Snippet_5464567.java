@Unstable
public interface OffloadIndexBlock extends Closeable {

    /**
     * Get the content of the index block as InputStream.
     * Read out in format:
     *   | index_magic_header | index_block_len | index_entry_count |
     *   | data_object_size | segment_metadata_length | segment metadata | index entries ... |
     */
    IndexInputStream toStream() throws IOException;

    /**
     * Get the related OffloadIndexEntry that contains the given messageEntryId.
     *
     * @param messageEntryId
     *                      the entry id of message
     * @return the offload index entry
     */
    OffloadIndexEntry getIndexEntryForEntry(long messageEntryId) throws IOException;

    /**
     * Get the entry count that contained in this index Block.
     */
    int getEntryCount();

    /**
     * Get LedgerMetadata.
     */
    LedgerMetadata getLedgerMetadata();

    /**
     * Get the total size of the data object.
     */
    long getDataObjectLength();

    /**
     * Get the length of the header in the blocks in the data object.
     */
    long getDataBlockHeaderLength();

    /**
     * An input stream which knows the size of the stream upfront.
     */
    public static class IndexInputStream extends FilterInputStream {
        final long streamSize;

        public IndexInputStream(InputStream in, long streamSize) {
            super(in);
            this.streamSize = streamSize;
        }

        /**
         * @return the number of bytes in the stream.
         */
        public long getStreamSize() {
            return streamSize;
        }
    }
}
