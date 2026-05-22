public interface ClientUpdater {

  void close();

  boolean isAlive();

  void join(long wait) throws InterruptedException;

  void setFailedUpdater(ClientUpdater failedUpdater);

  boolean isProcessing();

  boolean isPrimary();
}
