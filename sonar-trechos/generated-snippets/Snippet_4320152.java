private static class KeyComparator {

        /** Number of rows fetched per iteration. */
        private static final int FETCH_SIZE = 16;
        private final DataValueDescriptor[][] rowBufferArray;
        private DataValueDescriptor[] lastUniqueKey;
        private DataValueDescriptor[] curr;
        private DataValueDescriptor[] prev;
        private int rowsReadLastRead = -1;
        private long numRows;

        /**
         * Creates a key comparator for the given index.
         *
         * @param ir index row (template)
         */
        public KeyComparator(ExecIndexRow ir) {
            rowBufferArray = new DataValueDescriptor[FETCH_SIZE][];
            rowBufferArray[0] = ir.getRowArray(); // 1 gets old objects.
            lastUniqueKey = ir.getRowArrayClone();
        }

        /**
         * Fetches rows from the scan controller.
         *
         * @param gsc the scan controller
         * @return Number of rows fetched.
         * @throws StandardException if fetching rows fails
         */
        public int fetchRows(GroupFetchScanController gsc)
                throws StandardException {
            // Save state (and optimize) for next read.
            // Assumes that we always read as many rows as we can per iteration.
            if (rowsReadLastRead == FETCH_SIZE) {
                // Reuse curr to reference the current last unique key.
                curr = rowBufferArray[FETCH_SIZE - 1];
                // Reuse the old last unique row array for the coming fetch.
                rowBufferArray[FETCH_SIZE - 1] = lastUniqueKey;
                // Finally we update the pointer to the last unique key.
                lastUniqueKey = curr;
            }
            rowsReadLastRead = gsc.fetchNextGroup(rowBufferArray, null);
            return rowsReadLastRead;
        }

        /**
         * Compares the key at the specified index with the previous key.
         *
         * @param index row index
         * @return {@code -1} if the current and previous key are identical,
         *      the index of the changed part of the key otherwise
         *      ([0, key length&gt;)
         * @throws StandardException if comparing the two keys fails
         */
        public int compareWithPrevKey(int index)
                throws StandardException {
            if (index > rowsReadLastRead) {
                throw new IllegalStateException(
                        "invalid access, rowsReadLastRead=" + rowsReadLastRead +
                        ", index=" + index + ", numRows=" + numRows);
            }
            numRows++;
            // First row ever is always a distinct key.
            if (numRows == 1) {
                return 0;
            }

            prev = (index == 0) ? lastUniqueKey
                                : rowBufferArray[index - 1];
            curr = rowBufferArray[index];
            DataValueDescriptor dvd;
            // no point trying to do rowlocation; hence - 1
            for (int i = 0; i < (prev.length - 1); i++) {
                dvd = (DataValueDescriptor)prev[i];

                // NULLs are counted as unique values.
                if (dvd.isNull() || prev[i].compare(curr[i]) != 0) {
                  return i;
                }
            }
            return -1;
        }

        /**
         * Returns the number of rows fetched.
         *
         * @return Number of rows fetched.
         */
        public long getRowCount() {
            return numRows;
        }
    }
