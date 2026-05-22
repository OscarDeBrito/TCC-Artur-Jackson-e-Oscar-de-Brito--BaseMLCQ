public class Snippet__7810336 {

    private void rehash(Entry[] oldMap, int newCount, int newCapacity) {
        int oldCapacity = oldMap.length;

        Entry newMap[] = new Entry[newCapacity];

        synchronized (rehashLock) {
          for (int i = oldCapacity; i-- > 0;) {
            for (Entry old = oldMap[i]; old != null;) {
              Entry e = old;
              old = old.next;

              if (e.value != null && e.value instanceof WeakReference) {
                WeakReference r = (WeakReference) e.value;
                if (r.get() == null) {
                  // don't copy this one into the new table since its value was gc'd
                  newCount--;
                  continue;
                }
              }
              int index = Math.abs(e.key) % newCapacity;
              e.next = newMap[index];
              newMap[index] = e;
            }
          }

          threshold = (int) (newCapacity * loadFactor);
          count = newCount;
          table = newMap;
        }
      }

}
