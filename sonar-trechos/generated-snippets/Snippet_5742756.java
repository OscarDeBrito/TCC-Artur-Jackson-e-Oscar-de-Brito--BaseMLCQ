public class Snippet__5742756 {

    public abstract static class TreeReader {
        protected final int columnId;
        protected BitFieldReader present = null;
        protected boolean valuePresent = false;

        TreeReader(int columnId) throws IOException {
          this(columnId, null);
        }

        protected TreeReader(int columnId, InStream in) throws IOException {
          this.columnId = columnId;
          if (in == null) {
            present = null;
            valuePresent = true;
          } else {
            present = new BitFieldReader(in, 1);
          }
        }

        void checkEncoding(OrcProto.ColumnEncoding encoding) throws IOException {
          if (encoding.getKind() != OrcProto.ColumnEncoding.Kind.DIRECT) {
            throw new IOException("Unknown encoding " + encoding + " in column " +
                columnId);
          }
        }

        static IntegerReader createIntegerReader(OrcProto.ColumnEncoding.Kind kind,
                                                 InStream in,
                                                 boolean signed, boolean skipCorrupt) throws IOException {
          switch (kind) {
            case DIRECT_V2:
            case DICTIONARY_V2:
              return new RunLengthIntegerReaderV2(in, signed, skipCorrupt);
            case DIRECT:
            case DICTIONARY:
              return new RunLengthIntegerReader(in, signed);
            default:
              throw new IllegalArgumentException("Unknown encoding " + kind);
          }
        }

        void startStripe(Map<org.apache.orc.impl.StreamName, InStream> streams,
                         OrcProto.StripeFooter stripeFooter
        ) throws IOException {
          checkEncoding(stripeFooter.getColumnsList().get(columnId));
          InStream in = streams.get(new org.apache.orc.impl.StreamName(columnId,
              OrcProto.Stream.Kind.PRESENT));
          if (in == null) {
            present = null;
            valuePresent = true;
          } else {
            present = new BitFieldReader(in, 1);
          }
        }

        /**
         * Seek to the given position.
         *
         * @param index the indexes loaded from the file
         * @throws IOException
         */
        void seek(PositionProvider[] index) throws IOException {
          seek(index[columnId]);
        }

        public void seek(PositionProvider index) throws IOException {
          if (present != null) {
            present.seek(index);
          }
        }

        protected long countNonNulls(long rows) throws IOException {
          if (present != null) {
            long result = 0;
            for (long c = 0; c < rows; ++c) {
              if (present.next() == 1) {
                result += 1;
              }
            }
            return result;
          } else {
            return rows;
          }
        }

        abstract void skipRows(long rows) throws IOException;

        public BitFieldReader getPresent() {
          return present;
        }
      }

}
