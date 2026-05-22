final class CacheImpl<K, V> extends Cache<K,V> {
    final private Map<Reference<K>, Entry<V>> objectCache = new HashMap<Reference<K>, Entry<V>>();

    private long update_interval = 60480000; // 7 days in milliseconds

    private Persistor<K, V> persistor = Persistor.DEFAULT;
    private KeyFactory<K> keyFactory = KeyFactory.DEFAULT;
    private EntryFactory<K,V> resolver = EntryFactory.DEFAULT;

    CacheImpl() {};

    CacheImpl(EntryFactory<K,V> resolver) {
        this.resolver = resolver;
    }

    CacheImpl(Persistor<K,V> persistor) {
        this.persistor = persistor;
    }

    CacheImpl(KeyFactory<K> keyFactory) {
        this.keyFactory = keyFactory;
    }

    CacheImpl(EntryFactory<K,V> resolver, Persistor<K,V> persistor) {
        this.resolver = resolver;
        this.persistor = persistor;
    }

    CacheImpl(EntryFactory<K,V> resolver, KeyFactory<K> keyFactory) {
        this.resolver = resolver;
        this.keyFactory = keyFactory;
    }

    CacheImpl(KeyFactory<K> keyFactory, Persistor<K,V> persistor) {
        this.persistor = persistor;
        this.keyFactory = keyFactory;
    }

    CacheImpl(EntryFactory<K,V> resolver, KeyFactory<K> keyFactory, Persistor<K,V> persistor) {
        this.resolver = resolver;
        this.persistor = persistor;
        this.keyFactory = keyFactory;
    }

    /**
     * Retrieves an object from the cache by the given key
     * <p>
     * If there is no cached version then a registered instance of {@linkplain EntryFactory}
     * is used to invoke its {@linkplain EntryFactory#createEntry(java.lang.Object)} method.<br/>
     * Also, a {@linkplain Persistor} instance is used to retrieve and store the cached value in
     * a dedicated storage.
     * </p>
     * @param key The key identifying the object to be retrieved
     * @return Returns the cached object or NULL
     */
    @Override
    final public V retrieveObject(K key) {
        Reference<K> softKey = keyFactory.createKey(key);
        synchronized(objectCache) {
            Entry<V> entry = objectCache.get(softKey);
            if (entry == null) {
                entry = persistor.retrieve(key);
            }
            if (entry == null) {
                entry = cacheMiss(key);
                if (entry != null && entry.getContent() != null) {
                    persistor.store(key, entry);
                    objectCache.put(softKey, entry);
                }
            } else {
                long timestamp = System.currentTimeMillis();
                if ((timestamp - entry.getUpdateTimeStamp()) > update_interval) {
                    Entry<V> newEntry = cacheMiss(key);
                    if (newEntry != null && newEntry.getContent() != null) {
                        persistor.store(key, entry);
                        objectCache.put(softKey, newEntry);
                        entry = newEntry;
                    }
                }
            }
            return entry != null ? entry.getContent() : null;
        }
    }
    
    @Override
    final public V invalidateObject(K key) {
        Reference<K> softKey = keyFactory.createKey(key);
        synchronized(objectCache) {
            Entry<V> entry = objectCache.remove(softKey);
            return entry != null ? entry.getContent() : null;
        }
    }

    /**
     * Property getter
     * @return Returns TTL interval in milliseconds
     */
    @Override
    final public long getTTL() {
        return update_interval;
    }

    /**
     * Property setter
     * @param ttl TTL interval in milliseconds
     */
    @Override
    final public void setTTL(long ttl) {
        this.update_interval = ttl;
    }

    /**
     * This method is called in case of cache-miss
     * It can return NULL if it's not possible to resolve the missing instance
     * @param key The key of the missing object
     * @return Returns the resolved object or NULL
     */
    private Entry<V> cacheMiss(K key) {
        return resolver.createEntry(key);
    }
}
