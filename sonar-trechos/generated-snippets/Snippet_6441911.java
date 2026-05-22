public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addStandaloneView(OrionConsoleView.ID, true, IPageLayout.LEFT, 0.5f, "org.eclipse.ui.editorss");
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
	}
}
