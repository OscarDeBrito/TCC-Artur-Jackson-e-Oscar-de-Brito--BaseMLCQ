public interface Range<T extends Comparable<?>, S> extends Iterable<T> {

    /**
     * Default left bound type.
     */
    BoundType DEFAULT_LEFT_BOUND_TYPE = BoundType.CLOSED;

    /**
     * Default right bound type.
     */
    BoundType DEFAULT_RIGHT_BOUND_TYPE = BoundType.OPEN;

    /**
     * Get the left limit of this range.
     *
     * @return Endpoint
     */
    Endpoint<T> getLeftEndpoint();

    /**
     * Get the right limit of this range.
     *
     * @return Endpoint
     */
    Endpoint<T> getRightEndpoint();

    /**
     * Get the step between elements of this range.
     *
     * @return Number
     */
    S getStep();

    /**
     * Returns <code>true</code> if this range is empty.
     *
     * @return <code>true</code> if this range is empty
     */
    boolean isEmpty();

    /**
     * Returns <code>true</code> if this range contains the specified element.
     *
     * @param obj element whose presence is being tested in this range
     * @return <code>true</code> if this range contains the specified element
     */
    boolean contains(T obj);

    /**
     * Returns <code>true</code> is this range contains all of the elements in
     * the specified collection.
     *
     * @param col collection to be checked for the containment in this range
     * @return <code>true</code> if this range contains all of the elements in
     * the specified collection
     */
    boolean containsAll(Collection<T> col);
}
