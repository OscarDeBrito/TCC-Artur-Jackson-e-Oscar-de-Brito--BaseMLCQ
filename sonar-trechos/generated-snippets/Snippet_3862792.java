@Singleton
public class TimelineMetricCacheProvider {
  private TimelineMetricCache timelineMetricsCache;
  private volatile boolean isCacheInitialized = false;
  public static final String TIMELINE_METRIC_CACHE_MANAGER_NAME = "timelineMetricCacheManager";
  public static final String TIMELINE_METRIC_CACHE_INSTANCE_NAME = "timelineMetricCache";

  Configuration configuration;
  TimelineMetricCacheEntryFactory cacheEntryFactory;

  private final static Logger LOG = LoggerFactory.getLogger(TimelineMetricCacheProvider.class);

  @Inject
  public TimelineMetricCacheProvider(Configuration configuration,
                                     TimelineMetricCacheEntryFactory cacheEntryFactory) {
    this.configuration = configuration;
    this.cacheEntryFactory = cacheEntryFactory;
  }

  private synchronized void initializeCache() {
    // Check in case of contention to avoid ObjectExistsException
    if (isCacheInitialized) {
      return;
    }

    System.setProperty("net.sf.ehcache.skipUpdateCheck", "true");
    if (configuration.useMetricsCacheCustomSizingEngine()) {
      // Use custom sizing engine to speed cache sizing calculations
      System.setProperty("net.sf.ehcache.sizeofengine." + TIMELINE_METRIC_CACHE_MANAGER_NAME,
        "org.apache.ambari.server.controller.metrics.timeline.cache.TimelineMetricsCacheSizeOfEngine");
    }

    net.sf.ehcache.config.Configuration managerConfig =
      new net.sf.ehcache.config.Configuration();
    managerConfig.setName(TIMELINE_METRIC_CACHE_MANAGER_NAME);

    // Set max heap available to the cache manager
    managerConfig.setMaxBytesLocalHeap(configuration.getMetricsCacheManagerHeapPercent());

    //Create a singleton CacheManager using defaults
    CacheManager manager = CacheManager.create(managerConfig);

    LOG.info("Creating Metrics Cache with timeouts => ttl = " +
      configuration.getMetricCacheTTLSeconds() + ", idle = " +
      configuration.getMetricCacheIdleSeconds());

    // Create a Cache specifying its configuration.
    CacheConfiguration cacheConfiguration = createCacheConfiguration();
    Cache cache = new Cache(cacheConfiguration);

    // Decorate with UpdatingSelfPopulatingCache
    timelineMetricsCache = new TimelineMetricCache(cache, cacheEntryFactory);

    LOG.info("Registering metrics cache with provider: name = " +
      cache.getName() + ", guid: " + cache.getGuid());

    manager.addCache(timelineMetricsCache);

    isCacheInitialized = true;
  }

  // Having this as a separate public method for testing/mocking purposes
  public CacheConfiguration createCacheConfiguration() {

    CacheConfiguration cacheConfiguration = new CacheConfiguration()
      .name(TIMELINE_METRIC_CACHE_INSTANCE_NAME)
      .timeToLiveSeconds(configuration.getMetricCacheTTLSeconds()) // 1 hour
      .timeToIdleSeconds(configuration.getMetricCacheIdleSeconds()) // 5 minutes
      .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
      .sizeOfPolicy(new SizeOfPolicyConfiguration() // Set sizeOf policy to continue on max depth reached - avoid OOM
        .maxDepth(10000)
        .maxDepthExceededBehavior(MaxDepthExceededBehavior.CONTINUE))
      .eternal(false)
      .persistence(new PersistenceConfiguration()
        .strategy(Strategy.NONE.name()));

    return cacheConfiguration;
  }

  /**
   * Return an instance of a Ehcache
   * @return @TimelineMetricCache or null if caching is disabled through config.
   */
  public TimelineMetricCache getTimelineMetricsCache() {
    if (configuration.isMetricsCacheDisabled()) {
      return null;
    }

    if (!isCacheInitialized) {
      initializeCache();
    }
    return timelineMetricsCache;
  }

}
