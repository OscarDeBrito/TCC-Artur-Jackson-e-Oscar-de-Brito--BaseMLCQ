public interface Future {

  /**
   * Non-blocking get.
   *
   * @return the future value, which may be {@code null} if it has not been resolved yet.
   */
  Object get();

  /**
   * Blocking get, waiting until the future has been resolved.
   *
   * @return the future value.
   * @throws InterruptedException when the current thread is being interrupted.
   */
  Object blockingGet() throws InterruptedException;

  /**
   * Test whether the future has been resolved, that is, the future is either set or failed.
   *
   * @return {@code true} if the future is resolved, {@code false} otherwise.
   */
  boolean isResolved();

  /**
   * Test whether the future has failed.
   *
   * @return {@code true} if the future is resolved and failed, {@code false} otherwise.
   */
  boolean isFailed();

  /**
   * Registers a callback for when the future is set. If the future has already been set, then it is executed
   * immediately from the caller thread.
   *
   * @param observer the callback.
   * @return this future object.
   */
  Future onSet(Observer observer);

  /**
   * Registers a callback for when the future fails. If the future has already been failed, then it is executed
   * immediately from the caller thread.
   *
   * @param observer the callback.
   * @return this future object.
   */
  Future onFail(Observer observer);

  /**
   * Simple interface for a future observer / callback.
   */
  @FunctionalInterface
  interface Observer {

    /**
     * Callback method.
     *
     * @param value the future value.
     */
    void apply(Object value);
  }
}
