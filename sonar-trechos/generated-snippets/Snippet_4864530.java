public class DoubleGroupByResultHolder implements GroupByResultHolder {
  private final int _maxCapacity;
  private final double _defaultValue;

  private int _resultHolderCapacity;
  private double[] _resultArray;

  /**
   * Constructor for the class.
   *
   * @param initialCapacity Initial capacity of the result holder
   * @param maxCapacity Maximum capacity of the result holder
   * @param defaultValue Default value of un-initialized results
   */
  public DoubleGroupByResultHolder(int initialCapacity, int maxCapacity, double defaultValue) {
    _maxCapacity = maxCapacity;
    _defaultValue = defaultValue;

    _resultHolderCapacity = initialCapacity;
    _resultArray = new double[initialCapacity];
    if (defaultValue != 0.0) {
      Arrays.fill(_resultArray, defaultValue);
    }
  }

  @Override
  public void ensureCapacity(int capacity) {
    Preconditions.checkArgument(capacity <= _maxCapacity);

    if (capacity > _resultHolderCapacity) {
      int copyLength = _resultHolderCapacity;
      _resultHolderCapacity = Math.max(_resultHolderCapacity * 2, capacity);

      // Cap the growth to maximum possible number of group keys
      _resultHolderCapacity = Math.min(_resultHolderCapacity, _maxCapacity);

      double[] current = _resultArray;
      _resultArray = new double[_resultHolderCapacity];
      System.arraycopy(current, 0, _resultArray, 0, copyLength);

      if (_defaultValue != 0.0) {
        Arrays.fill(_resultArray, copyLength, _resultHolderCapacity, _defaultValue);
      }
    }
  }

  @Override
  public double getDoubleResult(int groupKey) {
    if (groupKey == GroupKeyGenerator.INVALID_ID) {
      return _defaultValue;
    } else {
      return _resultArray[groupKey];
    }
  }

  @Override
  public <T> T getResult(int groupKey) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setValueForKey(int groupKey, double newValue) {
    if (groupKey != GroupKeyGenerator.INVALID_ID) {
      _resultArray[groupKey] = newValue;
    }
  }

  @Override
  public void setValueForKey(int groupKey, Object newValue) {
    throw new UnsupportedOperationException();
  }
}
