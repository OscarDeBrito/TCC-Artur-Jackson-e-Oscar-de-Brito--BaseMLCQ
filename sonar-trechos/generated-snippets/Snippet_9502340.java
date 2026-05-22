public class Snippet__9502340 {

    public void removeFileBeingWritten(final long inodeId) {
        synchronized(filesBeingWritten) {
          filesBeingWritten.remove(inodeId);
          if (filesBeingWritten.isEmpty()) {
            lastLeaseRenewal = 0;
          }
        }
      }

}
