public class Snippet__7093536 {

    private void siftUp(int k, RunnableScheduledFuture<?> key) {
                while (k > 0) {
                    int parent = (k - 1) >>> 1;
                    RunnableScheduledFuture<?> e = queue[parent];
                    if (key.compareTo(e) >= 0)
                        break;
                    queue[k] = e;
                    setIndex(e, k);
                    k = parent;
                }
                queue[k] = key;
                setIndex(key, k);
            }

}
