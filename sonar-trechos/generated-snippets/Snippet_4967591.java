public class MultiIterable<E> implements Iterable<E> {

	final List<Iterator<E>> iterators = new LinkedList<>();

	/**
	 * Constructor.
	 *
	 * @param iterators The list of iterators to iterate over.
	 */
	@SuppressWarnings("unchecked")
	public MultiIterable(Iterator<E>...iterators) {
		for (Iterator<E> i : iterators)
			append(i);
	}

	/**
	 * Appends the specified iterator to this list of iterators.
	 *
	 * @param iterator The iterator to append.
	 * @return This object (for method chaining).
	 */
	public MultiIterable<E> append(Iterator<E> iterator) {
		assertFieldNotNull(iterator, "iterator");
		this.iterators.add(iterator);
		return this;
	}

	@Override /* Iterable */
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Iterator<Iterator<E>> i1 = iterators.iterator();
			Iterator<E> i2 = i1.hasNext() ? i1.next() : null;

			@Override /* Iterator */
			public boolean hasNext() {
				while (i2 != null && ! i2.hasNext())
					i2 = (i1.hasNext() ? i1.next() : null);
				return (i2 != null);
			}

			@Override /* Iterator */
			public E next() {
				hasNext();
				if (i2 == null)
					throw new NoSuchElementException();
				return i2.next();
			}

			@Override /* Iterator */
			public void remove() {
				if (i2 == null)
					throw new NoSuchElementException();
				i2.remove();
			}
		};
	}
}
