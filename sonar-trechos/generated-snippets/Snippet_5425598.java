public abstract class SizeBoundQueue<T> extends AbstractQueue<T> implements SizeAwareQueue<T> {

    private long maxSizeBytes;
    private Queue<T> delegate;
    private long currentSize;

    public SizeBoundQueue(long maxSizeBytes, Queue<T> delegate) {
        assert maxSizeBytes > 0;
        this.maxSizeBytes = maxSizeBytes;
        this.delegate = delegate;
    }

    abstract public long sizeOf(T e);

    @Override
    public boolean offer(T e) {
        boolean success = false;
        long elementSize = sizeOf(e);
        if ((currentSize + elementSize) < maxSizeBytes) {
            success = delegate.offer(e);
            if (success) {
                currentSize += elementSize;
            }
        }
        return success;
    }

    @Override
    public boolean add(T e) {
        try {
            return super.add(e);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException(
                    "Queue full. Consider increasing memory threshold or spooling to disk", ex);
        }
    }

    @Override
    public T poll() {
        T e = delegate.poll();
        if (e != null) {
            currentSize -= sizeOf(e);
        }
        return e;
    }

    @Override
    public T peek() {
        return delegate.peek();
    }

    @Override
    public void close() throws IOException {
        delegate.clear();
    }

    @Override
    public long getByteSize() {
        return currentSize;
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    public int size() {
        return delegate.size();
    }

}
