public class Snippet__7539445 {

    @Override
            public boolean hasNext() {
                if (nextValue != null) {
                    return true;
                }
                while (it.hasNext()) {
                    Map.Entry<Key<K>, V> entry = it.next();
                    K key = entry.getKey().get();
                    if (key != null) {
                        nextValue = new Entry(key, entry.getValue());
                        return true;
                    } else {
                        it.remove();
                    }
                }
                return false;
            }

}
