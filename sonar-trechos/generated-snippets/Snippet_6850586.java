public final class CFilterByEdgesAction extends AbstractAction {
  /**
   * Used for serialization.
   */
  private static final long serialVersionUID = 8570733381669936465L;

  /**
   * The filter field where the text is inserted.
   */
  private final JTextField m_filterField;

  /**
   * Creates a new action object.
   * 
   * @param filterField The filter field where the text is inserted.
   */
  public CFilterByEdgesAction(final JTextField filterField) {
    super("Filter by edge count");

    m_filterField = filterField;
  }

  @Override
  public void actionPerformed(final ActionEvent event) {
    TextHelpers.insert(m_filterField, "edges==0");
  }
}
