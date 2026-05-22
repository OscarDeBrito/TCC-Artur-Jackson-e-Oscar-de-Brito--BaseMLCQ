private static class MapKeyFunction<K> implements Function<Map.Entry<K, ?>, K>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public K valueOf(Map.Entry<K, ?> entry)
        {
            return entry.getKey();
        }
    }
