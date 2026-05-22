public class Snippet__8566692 {

    @Override
      public boolean hasReferences() {
        // Grab the read lock here, because we need to ensure that: only when the atomic
        // replaceStoreFiles(..) finished, we can get all the complete store file list.
        this.lock.readLock().lock();
        try {
          // Merge the current store files with compacted files here due to HBASE-20940.
          Collection<HStoreFile> allStoreFiles = new ArrayList<>(getStorefiles());
          allStoreFiles.addAll(getCompactedFiles());
          return StoreUtils.hasReferences(allStoreFiles);
        } finally {
          this.lock.readLock().unlock();
        }
      }

}
