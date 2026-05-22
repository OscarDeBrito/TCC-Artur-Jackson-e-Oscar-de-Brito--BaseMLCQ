private class ValueCollection
        extends AbstractCollection {

        @Override
        public int size() {
            return CacheMap.this.size();
        }

        @Override
        public Iterator iterator() {
            return new EntryIterator(EntryIterator.VALUE);
        }
    }
