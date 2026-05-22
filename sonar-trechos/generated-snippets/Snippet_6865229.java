public final class ProgressDialog {
  /**
   * Do not create objects of this class.
   */
  private ProgressDialog() {
    // You are not supposed to instantiate this class
  }

  // ! Shows a progress dialog.
  /**
   * Shows a progress dialog.
   *
   * @param parent The parent window of the progress dialog.
   * @param title The title of the progress dialog.
   * @param thread The thread which implements the long-running operation.
   *
   * @return The exception thrown during the execution of IProgressThread::run or null if no
   *         exception was thrown.
   */
  public static Exception show(
      final Window parent, final String title, final IProgressThread thread) {
    Preconditions.checkNotNull(thread, "Error: Thread argument can't be null");

    final EndlessHelperWrapper helperThread = new EndlessHelperWrapper(thread);

    CProgressDialog.showEndless(parent, title, helperThread);

    return helperThread.getException();
  }

  /**
   * Wraps endless helper threads.
   */
  private static class EndlessHelperWrapper extends CEndlessHelperThread {
    /**
     * The wrapped API thread object.
     */
    private final IProgressThread m_thread;

    /**
     * Creates a new wrapper object.
     *
     * @param thread The wrapped API thread object.
     */
    private EndlessHelperWrapper(final IProgressThread thread) {
      m_thread = thread;
    }

    @Override
    protected void runExpensiveCommand() throws Exception {
      m_thread.run();
    }

    @Override
    public void closeRequested() {
      if (m_thread.close()) {
        finish();
      }
    }
  }
}
