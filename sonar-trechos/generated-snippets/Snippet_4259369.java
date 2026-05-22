public class Snippet__4259369 {

    @Override
        public Map<String,Integer> getNumWaitersByKey() {
            final Map<String,Integer> result = new HashMap<>();

            for (final Map.Entry<K, ObjectDeque<T>> entry : poolMap.entrySet()) {
                final K k = entry.getKey();
                final ObjectDeque<T> deque = entry.getValue();
                if (deque != null) {
                    if (getBlockWhenExhausted()) {
                        result.put(k.toString(), Integer.valueOf(
                                deque.getIdleObjects().getTakeQueueLength()));
                    } else {
                        result.put(k.toString(), Integer.valueOf(0));
                    }
                }
            }
            return result;
        }

}
