public class TableConfigCache {

  // TODO: Make cache size, timeout configurable through controller config.
  private static final long DEFAULT_CACHE_SIZE = 50;
  private static final long DEFAULT_CACHE_TIMEOUT_IN_MINUTE = 60;

  private final LoadingCache<String, TableConfig> _tableConfigCache;
  private final ZkHelixPropertyStore<ZNRecord> _propertyStore;

  public TableConfigCache(ZkHelixPropertyStore<ZNRecord> propertyStore) {
    _tableConfigCache = CacheBuilder.newBuilder().maximumSize(DEFAULT_CACHE_SIZE)
        .expireAfterWrite(DEFAULT_CACHE_TIMEOUT_IN_MINUTE, TimeUnit.MINUTES)
        .build(new CacheLoader<String, TableConfig>() {
          @Override
          public TableConfig load(String tableNameWithType)
              throws Exception {
            return ZKMetadataProvider.getTableConfig(_propertyStore, tableNameWithType);
          }
        });
    _propertyStore = propertyStore;
  }

  public TableConfig getTableConfig(String tableNameWithType)
      throws ExecutionException {
    return _tableConfigCache.get(tableNameWithType);
  }
}
