public class OffHeapPollingCache<K, V> implements PollingCache<K, V>
{
  private static final DB DB = DBMaker.newMemoryDirectDB().transactionDisable().closeOnJvmShutdown().make();

  private final HTreeMap<K, V> mapCache;
  private final HTreeMap<V, List<K>> reverseCache;
  private final AtomicBoolean started = new AtomicBoolean(false);
  private final String cacheName;
  private final String reverseCacheName;

  public OffHeapPollingCache(final Iterable<Map.Entry<K, V>> entries)
  {
    synchronized (started) {
      this.cacheName = StringUtils.format("cache-%s", UUID.randomUUID());
      this.reverseCacheName = StringUtils.format("reverseCache-%s", UUID.randomUUID());
      mapCache = DB.createHashMap(cacheName).make();
      reverseCache = DB.createHashMap(reverseCacheName).make();
      ImmutableSet.Builder<V> setOfValuesBuilder = ImmutableSet.builder();
      for (Map.Entry<K, V> entry : entries) {
        mapCache.put(entry.getKey(), entry.getValue());
        setOfValuesBuilder.add(entry.getValue());
      }

      final Set<V> setOfValues = setOfValuesBuilder.build();
      reverseCache.putAll(Maps.asMap(
          setOfValues,
          new Function<V, List<K>>()
          {
            @Override
            public List<K> apply(final V input)
            {
              return Lists.newArrayList(Maps.filterKeys(mapCache, new Predicate<K>()
              {
                @Override
                public boolean apply(K key)
                {
                  V retVal = mapCache.get(key);
                  if (retVal == null) {
                    return false;
                  }
                  return retVal.equals(input);
                }
              }).keySet());
            }
          }
      ));
      started.getAndSet(true);
    }
  }

  @Override
  public V get(K key)
  {
    return mapCache.get(key);
  }

  @Override
  public List<K> getKeys(V value)
  {
    final List<K> listOfKey = reverseCache.get(value);
    if (listOfKey == null) {
      return Collections.emptyList();
    }
    return listOfKey;
  }

  @Override
  public void close()
  {
    synchronized (started) {
      if (started.getAndSet(false)) {
        DB.delete(cacheName);
        DB.delete(reverseCacheName);
      }
    }
  }

  public static class OffHeapPollingCacheProvider<K, V> implements PollingCacheFactory<K, V>
  {
    @Override
    public PollingCache<K, V> makeOf(Iterable<Map.Entry<K, V>> entries)
    {
      return new OffHeapPollingCache<>(entries);
    }
  }
}
