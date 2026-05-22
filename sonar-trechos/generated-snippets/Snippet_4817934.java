public class ImportWorkflowAction extends AbstractAction {
	private static final long serialVersionUID = -2242979457902699028L;
	private final EditManager editManager;
	private final FileManager fileManager;
	private final MenuManager menuManager;
	private final ColourManager colourManager;
	private final WorkbenchConfiguration workbenchConfiguration;
	private final SelectionManager selectionManager;

	public ImportWorkflowAction(EditManager editManager, FileManager fileManager,
			MenuManager menuManager, ColourManager colourManager,
			WorkbenchConfiguration workbenchConfiguration, SelectionManager selectionManager) {
		super("Import workflow", DataflowActivityIcon.getDataflowIcon());
		this.editManager = editManager;
		this.fileManager = fileManager;
		this.menuManager = menuManager;
		this.colourManager = colourManager;
		this.workbenchConfiguration = workbenchConfiguration;
		this.selectionManager = selectionManager;
	}

	public void actionPerformed(ActionEvent e) {
		final Component parentComponent;
		if (e.getSource() instanceof Component) {
			parentComponent = (Component) e.getSource();
		} else {
			parentComponent = null;
		}
		ImportWorkflowWizard wizard = new ImportWorkflowWizard(
				Utils.getParentFrame(parentComponent), editManager, fileManager, menuManager,
				colourManager, workbenchConfiguration, selectionManager);
		wizard.setVisible(true);
	}

}
