public class ImmutableEntry<K, V> implements Map.Entry<K, V>  {
  private final K key;
  private final V value;

  public ImmutableEntry(final K key, final V value) {
    this.key = Preconditions.checkNotNull(key, "key is required");
    this.value = Preconditions.checkNotNull(value, "value is required");
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public V setValue(final V value) {
    throw new UnsupportedOperationException("entry is immutable");
  }

  @Override
  public boolean equals(final Object other) {
    if (other instanceof ImmutableEntry && other.getClass() == getClass()) {
      final ImmutableEntry<K, V> entry = (ImmutableEntry<K, V>)other;
      return Objects.equal(key, entry.key) && Objects.equal(value, entry.value);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(key, value);
  }
}
