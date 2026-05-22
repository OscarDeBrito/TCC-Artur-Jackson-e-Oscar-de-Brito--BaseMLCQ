@InterfaceAudience.Private
interface Cancellable {
  public void cancel();
  public boolean isCancelled();
}
