public class Snippet__4909319 {

    @Override
        public ConcurrentMap<K, V> asMap() {
            return new ConcurrentMap<K, V>() {

                @Override
                public int size() {
                    long size = CacheLIRS.this.size();
                    return (int) Math.min(size, Integer.MAX_VALUE);
                }

                @Override
                public boolean isEmpty() {
                    return CacheLIRS.this.size() == 0;
                }

                @Override
                public boolean containsKey(Object key) {
                    return CacheLIRS.this.containsKey(key);
                }

                @Override
                public boolean containsValue(Object value) {
                    return CacheLIRS.this.containsValue(value);
                }

                @SuppressWarnings("unchecked")
                @Override
                public V get(Object key) {
                    return CacheLIRS.this.peek((K) key);
                }

                @Override
                public V put(K key, V value) {
                    return CacheLIRS.this.put(key, value, sizeOf(key, value));
                }

                @Override
                public V remove(Object key) {
                    @SuppressWarnings("unchecked")
                    V old = CacheLIRS.this.getUnchecked((K) key);
                    CacheLIRS.this.invalidate(key);
                    return old;
                }

                @Override
                public void putAll(Map<? extends K, ? extends V> m) {
                    for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                        put(e.getKey(), e.getValue());
                    }                
                }

                @Override
                public void clear() {
                    CacheLIRS.this.clear();
                }

                @Override
                public Set<K> keySet() {
                    return CacheLIRS.this.keySet();
                }

                @Override
                public Collection<V> values() {
                    return CacheLIRS.this.values();
                }

                @Override
                public Set<java.util.Map.Entry<K, V>> entrySet() {
                    return CacheLIRS.this.entrySet();
                }

                @Override
                public V putIfAbsent(K key, V value) {
                    return CacheLIRS.this.putIfAbsent(key, value);
                }

                @Override
                public boolean remove(Object key, Object value) {
                    return CacheLIRS.this.remove(key, value);
                }

                @Override
                public boolean replace(K key, V oldValue, V newValue) {
                    return CacheLIRS.this.replace(key, oldValue, newValue);
                }

                @Override
                public V replace(K key, V value) {
                    return CacheLIRS.this.replace(key, value);
                }
            
            };
        }

}
