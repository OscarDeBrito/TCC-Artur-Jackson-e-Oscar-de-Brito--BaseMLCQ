public static class EmfCommentDataPlus implements EmfCommentData {
        private final List<HemfPlusRecord> records = new ArrayList<>();

        @Override
        public HemfCommentRecordType getCommentRecordType() {
            return HemfCommentRecordType.emfPlus;
        }

        @Override
        public long init(final LittleEndianInputStream leis, final long dataSize)
        throws IOException {
            long startIdx = leis.getReadIndex();
            int commentIdentifier = leis.readInt();
            assert (commentIdentifier == HemfCommentRecordType.emfPlus.id);
            new HemfPlusRecordIterator(leis, (int)dataSize-LittleEndianConsts.INT_SIZE).forEachRemaining(records::add);
            return leis.getReadIndex()-startIdx;
        }

        public List<HemfPlusRecord> getRecords() {
            return Collections.unmodifiableList(records);
        }
    }
