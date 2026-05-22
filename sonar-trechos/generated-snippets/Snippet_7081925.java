class CowSubList extends AbstractList<E> {

        /*
         * An immutable snapshot of a sub list's state. By gathering all three
         * of the sub list's fields in an immutable object,
         */
        private volatile Slice slice;

        public CowSubList(Object[] expectedElements, int from, int to) {
            this.slice = new Slice(expectedElements, from, to);
        }

        @Override public int size() {
            Slice slice = this.slice;
            return slice.to - slice.from;
        }

        @Override public boolean isEmpty() {
            Slice slice = this.slice;
            return slice.from == slice.to;
        }

        @SuppressWarnings("unchecked")
        @Override public E get(int index) {
            Slice slice = this.slice;
            Object[] snapshot = elements;
            slice.checkElementIndex(index);
            slice.checkConcurrentModification(snapshot);
            return (E) snapshot[index + slice.from];
        }

        @Override public Iterator<E> iterator() {
            return listIterator(0);
        }

        @Override public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override public ListIterator<E> listIterator(int index) {
            Slice slice = this.slice;
            Object[] snapshot = elements;
            slice.checkPositionIndex(index);
            slice.checkConcurrentModification(snapshot);
            CowIterator<E> result = new CowIterator<E>(snapshot, slice.from, slice.to);
            result.index = slice.from + index;
            return result;
        }

        @Override public int indexOf(Object object) {
            Slice slice = this.slice;
            Object[] snapshot = elements;
            slice.checkConcurrentModification(snapshot);
            int result = CopyOnWriteArrayList.indexOf(object, snapshot, slice.from, slice.to);
            return (result != -1) ? (result - slice.from) : -1;
        }

        @Override public int lastIndexOf(Object object) {
            Slice slice = this.slice;
            Object[] snapshot = elements;
            slice.checkConcurrentModification(snapshot);
            int result = CopyOnWriteArrayList.lastIndexOf(object, snapshot, slice.from, slice.to);
            return (result != -1) ? (result - slice.from) : -1;
        }

        @Override public boolean contains(Object object) {
            return indexOf(object) != -1;
        }

        @Override public boolean containsAll(Collection<?> collection) {
            Slice slice = this.slice;
            Object[] snapshot = elements;
            slice.checkConcurrentModification(snapshot);
            return CopyOnWriteArrayList.containsAll(collection, snapshot, slice.from, slice.to);
        }

        @Override public List<E> subList(int from, int to) {
            Slice slice = this.slice;
            if (from < 0 || from > to || to > size()) {
                throw new IndexOutOfBoundsException("from=" + from + ", to=" + to +
                        ", list size=" + size());
            }
            return new CowSubList(slice.expectedElements, slice.from + from, slice.from + to);
        }

        @Override public E remove(int index) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkElementIndex(index);
                slice.checkConcurrentModification(elements);
                E removed = CopyOnWriteArrayList.this.remove(slice.from + index);
                slice = new Slice(elements, slice.from, slice.to - 1);
                return removed;
            }
        }

        @Override public void clear() {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkConcurrentModification(elements);
                CopyOnWriteArrayList.this.removeRange(slice.from, slice.to);
                slice = new Slice(elements, slice.from, slice.from);
            }
        }

        @Override public void add(int index, E object) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkPositionIndex(index);
                slice.checkConcurrentModification(elements);
                CopyOnWriteArrayList.this.add(index + slice.from, object);
                slice = new Slice(elements, slice.from, slice.to + 1);
            }
        }

        @Override public boolean add(E object) {
            synchronized (CopyOnWriteArrayList.this) {
                add(slice.to - slice.from, object);
                return true;
            }
        }

        @Override public boolean addAll(int index, Collection<? extends E> collection) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkPositionIndex(index);
                slice.checkConcurrentModification(elements);
                int oldSize = elements.length;
                boolean result = CopyOnWriteArrayList.this.addAll(index + slice.from, collection);
                slice = new Slice(elements, slice.from, slice.to + (elements.length - oldSize));
                return result;
            }
        }

        @Override public boolean addAll(Collection<? extends E> collection) {
            synchronized (CopyOnWriteArrayList.this) {
                return addAll(size(), collection);
            }
        }

        @Override public E set(int index, E object) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkElementIndex(index);
                slice.checkConcurrentModification(elements);
                E result = CopyOnWriteArrayList.this.set(index + slice.from, object);
                slice = new Slice(elements, slice.from, slice.to);
                return result;
            }
        }

        @Override public boolean remove(Object object) {
            synchronized (CopyOnWriteArrayList.this) {
                int index = indexOf(object);
                if (index == -1) {
                    return false;
                }
                remove(index);
                return true;
            }
        }

        @Override public boolean removeAll(Collection<?> collection) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkConcurrentModification(elements);
                int removed = removeOrRetain(collection, false, slice.from, slice.to);
                slice = new Slice(elements, slice.from, slice.to - removed);
                return removed != 0;
            }
        }

        @Override public boolean retainAll(Collection<?> collection) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkConcurrentModification(elements);
                int removed = removeOrRetain(collection, true, slice.from, slice.to);
                slice = new Slice(elements, slice.from, slice.to - removed);
                return removed != 0;
            }
        }

        @Override
        public void forEach(Consumer<? super E> action) {
            CopyOnWriteArrayList.this.forInRange(slice.from, slice.to, action);
        }

        @Override
        public void replaceAll(UnaryOperator<E> operator) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkConcurrentModification(elements);
                CopyOnWriteArrayList.this.replaceInRange(slice.from, slice.to, operator);
                slice = new Slice(elements, slice.from, slice.to);
            }
        }

        @Override
        public synchronized void sort(Comparator<? super E> c) {
            synchronized (CopyOnWriteArrayList.this) {
                slice.checkConcurrentModification(elements);
                CopyOnWriteArrayList.this.sortInRange(slice.from, slice.to, c);
                slice = new Slice(elements, slice.from, slice.to);
            }
        }
    }
