public class VectorMapJoinOptimizedLongHashMultiSet
              extends VectorMapJoinOptimizedHashMultiSet
              implements VectorMapJoinLongHashMultiSet  {

  private VectorMapJoinOptimizedLongCommon longCommon;

  @Override
  public boolean useMinMax() {
    return longCommon.useMinMax();
  }

  @Override
  public long min() {
    return longCommon.min();
  }

  @Override
  public long max() {
    return longCommon.max();
  }

  /*
  @Override
  public void putRow(BytesWritable currentKey, BytesWritable currentValue)
      throws SerDeException, HiveException, IOException {

    longCommon.adaptPutRow((VectorMapJoinOptimizedHashTable) this, currentKey, currentValue);
  }
  */

  @Override
  public JoinResult contains(long key,
      VectorMapJoinHashMultiSetResult hashMultiSetResult) throws IOException {

    SerializedBytes serializedBytes = longCommon.serialize(key);

    return super.contains(serializedBytes.bytes, serializedBytes.offset, serializedBytes.length,
        hashMultiSetResult);

  }

  public VectorMapJoinOptimizedLongHashMultiSet(
        boolean minMaxEnabled, boolean isOuterJoin, HashTableKeyType hashTableKeyType,
        MapJoinTableContainer originalTableContainer, ReusableGetAdaptor hashMapRowGetter) {
    super(originalTableContainer, hashMapRowGetter);
    longCommon =  new VectorMapJoinOptimizedLongCommon(minMaxEnabled, isOuterJoin, hashTableKeyType);
  }
}
