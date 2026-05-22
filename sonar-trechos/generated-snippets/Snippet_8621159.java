private final class EntryIterator implements Iterator<Cache.Entry<K, V>> {

        /** Internal iterator. */
        private final Iterator<GridCacheMapEntry> internalIterator;

        /** Current entry. */
        private GridCacheMapEntry current;

        /** Keep binary flag. */
        private final boolean keepBinary;

        /**
         * Constructor.
         *
         * @param internalIterator Internal iterator.
         * @param keepBinary Keep binary.
         */
        private EntryIterator(Iterator<GridCacheMapEntry> internalIterator, boolean keepBinary) {
            this.internalIterator = internalIterator;
            this.keepBinary = keepBinary;
        }

        /** {@inheritDoc} */
        @Override public boolean hasNext() {
            return internalIterator.hasNext();
        }

        /** {@inheritDoc} */
        @Override public Cache.Entry<K, V> next() {
            current = internalIterator.next();

            return current.wrapLazyValue(keepBinary);
        }

        /** {@inheritDoc} */
        @Override public void remove() {
            if (current == null)
                throw new IllegalStateException();

            try {
                getAndRemove((K)current.wrapLazyValue(keepBinary).getKey());
            }
            catch (IgniteCheckedException e) {
                throw new IgniteException(e);
            }

            current = null;
        }
    }
