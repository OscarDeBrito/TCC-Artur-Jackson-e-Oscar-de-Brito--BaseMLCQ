public class TemporaryResultSetFactory {


  /**
   * Get a result set that is sorted. The result set will be overflowed on to disk as necessary, but
   * it will not be recovered from disk.
   *
   * @param extractor a callback to extract the index sort key from the object. The sort key is
   *        expected to be comparable.
   * @param reverse - true to reverse the natural order of the keys
   */
  public ResultSet getSortedResultSet(SortKeyExtractor extractor, boolean reverse) {
    return new SortedResultSetImpl(extractor, reverse);
  }

  /**
   * Get a result bag that is sorted. The result set will be overflowed on to disk as necessary, but
   * it will not be recovered from disk.
   *
   * @param extractor a callback to extract the index sort key from the object. The sort key is
   *        expected to be comparable.
   * @param reverse - true to reverse the natural order of the keys
   */
  public ResultBag getSortedResultBag(SortKeyExtractor extractor, boolean reverse) {
    return new SortedResultBagImpl(extractor, reverse);
  }

  /**
   * Get a result set that is not sorted. The result set will be overflowed on to disk as necessary,
   * but it will not be recovered from disk.
   *
   * This is useful for cases where the ordering is not important, but the set semantics are. For
   * example, a distinct query.
   *
   * @param reverse - true to reverse the natural order of the keys
   */
  public ResultSet getUnsortedResultSet(boolean reverse) {
    return new SortedResultSetImpl(null, reverse);
  }

  /**
   * Get a list to store temporary results. The list will be overflowed on to disk as necessary, but
   * it will not be recovered from disk.
   *
   */
  public ResultList getResultList() {
    return new ResultListImpl();
  }
}
