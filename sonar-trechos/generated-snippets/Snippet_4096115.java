public interface IInvertedIndexSearcher {
    /**
     * Searches the given inverted index using the search predicate and initializes the result cursor.
     */
    public void search(IIndexCursor resultCursor, InvertedIndexSearchPredicate searchPred, IIndexOperationContext ictx)
            throws HyracksDataException;

    /**
     * Continues the search process if it is paused. (e.g., output buffer full)
     *
     * @return true only if all search process is done.
     *         false otherwise.
     */
    public boolean continueSearch() throws HyracksDataException;

    public boolean hasNext() throws HyracksDataException;

    public void next() throws HyracksDataException;

    public void destroy() throws HyracksDataException;

    public ITupleReference getTuple();
}
