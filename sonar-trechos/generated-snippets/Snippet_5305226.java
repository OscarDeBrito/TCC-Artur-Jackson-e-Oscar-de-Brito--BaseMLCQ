static class TransactionalCache
        implements Set, Serializable {

        
        private static final long serialVersionUID = 1L;
        private final boolean _orderDirty;
        private Set<StateManagerImpl> _dirty = null;
        private Set<StateManagerImpl> _clean = null;

        public TransactionalCache(boolean orderDirty) {
            _orderDirty = orderDirty;
        }

        /**
         * Return a copy of all transactional state managers.
         */
        public Collection copy() {
            if (isEmpty()) {
                // Transaction Listeners may add entities to the transaction.
                return new LinkedHashSet();
            }

            // size may not be entirely accurate due to refs expiring, so
            // manually copy each object; doesn't matter this way if size too
            // big by some
            Set copy = new LinkedHashSet(size());
            if (_dirty != null)
                for (Iterator<StateManagerImpl> itr = _dirty.iterator(); itr.hasNext();)
                    copy.add(itr.next());
            if (_clean != null)
                for (Iterator<StateManagerImpl> itr = _clean.iterator(); itr.hasNext();)
                    copy.add(itr.next());
            return copy;
        }

        /**
         * Return a copy of all dirty state managers.
         */
        public Collection copyDirty() {
            if (_dirty == null || _dirty.isEmpty())
                return Collections.EMPTY_SET;
            return new LinkedHashSet<>(_dirty);
        }

        /**
         * Transfer the given instance from the dirty cache to the clean cache.
         */
        public void flushed(StateManagerImpl sm) {
            if (sm.isDirty() && _dirty != null && _dirty.remove(sm))
                addCleanInternal(sm);
        }

        /**
         * Add the given instance to the clean cache.
         */
        public void addClean(StateManagerImpl sm) {
            if (addCleanInternal(sm) && _dirty != null)
                _dirty.remove(sm);
        }

        private boolean addCleanInternal(StateManagerImpl sm) {
            if (_clean == null)
                _clean = new ReferenceHashSet(ReferenceStrength.SOFT);
            return _clean.add(sm);
        }

        /**
         * Add the given instance to the dirty cache.
         */
        public void addDirty(StateManagerImpl sm) {
            if (_dirty == null) {
                if (_orderDirty)
                    _dirty = MapBackedSet.mapBackedSet(new LinkedMap());
                else
                    _dirty = new HashSet<>();
            }
            if (_dirty.add(sm))
                removeCleanInternal(sm);
        }

        /**
         * Remove the given instance from the cache.
         */
        public boolean remove(StateManagerImpl sm) {
            return removeCleanInternal(sm)
                || (_dirty != null && _dirty.remove(sm));
        }

        private boolean removeCleanInternal(StateManagerImpl sm) {
            return _clean != null && _clean.remove(sm);
        }

        @Override
        public Iterator iterator() {
            IteratorChain chain = new IteratorChain();
            if (_dirty != null && !_dirty.isEmpty())
                chain.addIterator(_dirty.iterator());
            if (_clean != null && !_clean.isEmpty())
                chain.addIterator(_clean.iterator());
            return chain;
        }

        @Override
        public boolean contains(Object obj) {
            return (_dirty != null && _dirty.contains(obj))
                || (_clean != null && _clean.contains(obj));
        }

        @Override
        public boolean containsAll(Collection coll) {
            for (Iterator<?> itr = coll.iterator(); itr.hasNext();)
                if (!contains(itr.next()))
                    return false;
            return true;
        }

        @Override
        public void clear() {
            if (_dirty != null)
                _dirty = null;
            if (_clean != null)
                _clean = null;
        }

        @Override
        public boolean isEmpty() {
            return (_dirty == null || _dirty.isEmpty())
                && (_clean == null || _clean.isEmpty());
        }

        @Override
        public int size() {
            int size = 0;
            if (_dirty != null)
                size += _dirty.size();
            if (_clean != null)
                size += _clean.size();
            return size;
        }

        @Override
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection coll) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection coll) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object[] toArray(Object[] arr) {
            throw new UnsupportedOperationException();
        }
    }
