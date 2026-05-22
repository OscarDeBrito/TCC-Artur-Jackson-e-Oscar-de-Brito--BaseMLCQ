public class Snippet__6967137 {

    private void lockSourceAndCopy(File sourceFile, File copyFile) {
        sourceFile.opened();
        ReadWriteLock sourceLock = sourceFile.contentLock();
        if (sourceLock != null) {
          sourceLock.readLock().lock();
        }
        ReadWriteLock copyLock = copyFile.contentLock();
        if (copyLock != null) {
          copyLock.writeLock().lock();
        }
      }

}
