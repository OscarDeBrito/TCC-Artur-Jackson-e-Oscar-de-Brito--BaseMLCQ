public class LocalModelStorageProvider implements ModelStorageProvider {
    /** Storage of the files and directories. */
    private final ConcurrentMap<String, FileOrDirectory> storage = new ConcurrentHashMap<>();

    /** Storage of the locks. */
    private final ConcurrentMap<String, WeakReference<Lock>> locks = new ConcurrentHashMap<>();

    /** {@inheritDoc} */
    @Override public FileOrDirectory get(String key) {
        return storage.get(key);
    }

    /** {@inheritDoc} */
    @Override public void put(String key, FileOrDirectory file) {
        storage.put(key, file);
    }

    /** {@inheritDoc} */
    @Override public void remove(String key) {
        storage.remove(key);
    }

    /** {@inheritDoc} */
    @Override public Lock lock(String key) {
        Lock lock = new ReentrantLock();
        return locks.computeIfAbsent(key, k -> new WeakReference<>(lock)).get();
    }
}
