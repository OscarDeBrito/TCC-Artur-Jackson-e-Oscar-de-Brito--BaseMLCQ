public class CachingStatsSource implements StatsSource {


  private final Cache<OpTreeSignature, OperatorStats> cache;

  public CachingStatsSource(int cacheSize) {
    cache = CacheBuilder.newBuilder().maximumSize(cacheSize).build();
  }

  public void put(OpTreeSignature sig, OperatorStats opStat) {
    cache.put(sig, opStat);
  }

  @Override
  public Optional<OperatorStats> lookup(OpTreeSignature treeSig) {
    return Optional.ofNullable(cache.getIfPresent(treeSig));
  }

  @Override
  public boolean canProvideStatsFor(Class<?> clazz) {
    if (cache.size() > 0 && Operator.class.isAssignableFrom(clazz)) {
      return true;
    }
    return false;
  }

  @Override
  public void putAll(Map<OpTreeSignature, OperatorStats> map) {
    for (Entry<OpTreeSignature, OperatorStats> entry : map.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

}
