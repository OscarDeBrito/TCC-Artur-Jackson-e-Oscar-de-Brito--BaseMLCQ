public final class TestAutoFilterInfoRecord extends TestCase {
    private final byte[] data = new byte[] {
        0x05, 0x00
    };

    public void testRead() {

        AutoFilterInfoRecord record = new AutoFilterInfoRecord(TestcaseRecordInputStream.create(AutoFilterInfoRecord.sid, data));

        assertEquals(AutoFilterInfoRecord.sid, record.getSid());
        assertEquals(data.length, record.getDataSize());
        assertEquals(5, record.getNumEntries());
        record.setNumEntries((short)3);
        assertEquals(3, record.getNumEntries());
    }

    public void testWrite() {
        AutoFilterInfoRecord record = new AutoFilterInfoRecord();
        record.setNumEntries((short)3);

        byte [] ser = record.serialize();
        assertEquals(ser.length - 4, data.length);
        record = new AutoFilterInfoRecord(TestcaseRecordInputStream.create(ser));
        assertEquals(3, record.getNumEntries());
    }

    public void testClone()
    {
        AutoFilterInfoRecord record = new AutoFilterInfoRecord();
        record.setNumEntries((short)3);
        byte[] src = record.serialize();

        AutoFilterInfoRecord cloned = record.clone();
        assertEquals(3, record.getNumEntries());
        byte[] cln = cloned.serialize();

        assertEquals(record.getDataSize(), cloned.getDataSize());
        assertArrayEquals(src, cln);
    }
}
