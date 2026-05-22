public final class CDeleteBookmarkAction extends AbstractAction {
  /**
   * Used for serialization.
   */
  private static final long serialVersionUID = 3439106501997883212L;

  /**
   * Contains the debuggers where bookmarks can be set.
   */
  private final BackEndDebuggerProvider m_actionProvider;

  /**
   * Bookmark table rows that contains the bookmarks to delete.
   */
  private final int[] m_rows;

  /**
   * Creates a new action object.
   *
   * @param provider Contains the debuggers where bookmarks can be set.
   * @param rows Bookmark table rows that contains the bookmarks to delete.
   */
  public CDeleteBookmarkAction(final BackEndDebuggerProvider provider, final int[] rows) {
    super(rows.length == 1 ? "Delete Bookmark" : "Delete Bookmarks");

    m_actionProvider =
        Preconditions.checkNotNull(provider, "IE01328: Provider argument can not be null");

    m_rows = rows.clone();
  }

  @Override
  public void actionPerformed(final ActionEvent event) {
    CBookmarkFunctions.deleteBookmarks(m_actionProvider, m_rows);
  }
}
