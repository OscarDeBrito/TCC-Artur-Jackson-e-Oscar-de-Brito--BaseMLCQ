public final class CGraphPanelExtender {
  /**
   * You are not suppoed to instantiate this class.
   */
  private CGraphPanelExtender() {
  }

  /**
   * Registers extension objects.
   */
  public static void extend() {
    CAbstractGraphPanelExtensionFactory.register(new CVariablesExtensionCreator());
    CAbstractGraphPanelExtensionFactory.register(new CCrossReferenceExtensionCreator());
    CAbstractGraphPanelExtensionFactory.register(new CRegisterTrackingExtensionCreator());
    CAbstractGraphPanelExtensionFactory.register(new CInstructionHighlighterExtensionCreator());
    CAbstractGraphPanelExtensionFactory.register(new CCodeBookmarkExtensionCreator());
    // CAbstractGraphPanelExtensionFactory.register(new CRangeTrackingExtensionCreator());

  }
}
