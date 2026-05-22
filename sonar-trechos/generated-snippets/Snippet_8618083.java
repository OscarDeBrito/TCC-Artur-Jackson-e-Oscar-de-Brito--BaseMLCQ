public class ReplaceRecord<L> extends PageDeltaRecord {
    /** */
    private BPlusIO<L> io;

    /** */
    private byte[] rowBytes;

    /** */
    private int idx;

    /**
     * @param grpId Cache group ID.
     * @param pageId  Page ID.
     * @param io IO.
     * @param rowBytes Row bytes.
     * @param idx Index.
     */
    public ReplaceRecord(int grpId, long pageId, BPlusIO<L> io, byte[] rowBytes, int idx) {
        super(grpId, pageId);

        this.io = io;
        this.rowBytes = rowBytes;
        this.idx = idx;
    }

    /** {@inheritDoc} */
    @Override public void applyDelta(PageMemory pageMem, long pageAddr)
        throws IgniteCheckedException {
        if (io.getCount(pageAddr) < idx)
            throw new DeltaApplicationException("Index is greater than count: " + idx);

        io.store(pageAddr, idx, null, rowBytes, false);
    }

    /** {@inheritDoc} */
    @Override public RecordType type() {
        return RecordType.BTREE_PAGE_REPLACE;
    }

    /**
     * @return IO.
     */
    public BPlusIO<L> io() {
        return io;
    }

    /**
     * @return Index.
     */
    public int index() {
        return idx;
    }

    /**
     * @return Row bytes.
     */
    public byte[] rowBytes() {
        return rowBytes;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(ReplaceRecord.class, this, "super", super.toString());
    }
}
