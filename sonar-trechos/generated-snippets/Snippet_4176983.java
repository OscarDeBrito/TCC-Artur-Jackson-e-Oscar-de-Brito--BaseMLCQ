class ReferenceEntry extends SimpleEntry<K, V> {

        private static final long serialVersionUID = -1795136249842496011L;

        Entry<K, R> refEntry;

        public ReferenceEntry(Entry<K, R> refEntry, V value) {
            super(refEntry.getKey(), value);
            this.refEntry = refEntry;
        }

        @Override
        public V setValue(V value) {
            R newRef = newReference(value);
            R oldRef = refEntry.setValue(newRef);
            if(oldRef != null) {
                return getValue();
            }
            return null;
        }
    }
