@Singleton
public class CommandToolbarViewImpl implements CommandToolbarView {

  private static final CommandToolbarViewImplUiBinder UI_BINDER =
      GWT.create(CommandToolbarViewImplUiBinder.class);

  @UiField FlowPanel rootPanel;
  @UiField SimplePanel commandsPanel;
  @UiField SimplePanel processesListPanel;
  @UiField SimplePanel panelSelectorPanel;
  @UiField SimplePanel toolbarControllerPanel;
  @UiField SimplePanel buttonsPanel;
  @UiField SimplePanel previewUrlListPanel;

  private ActionDelegate delegate;

  @Inject
  public CommandToolbarViewImpl() {
    UI_BINDER.createAndBindUi(this);
  }

  @Override
  public void setDelegate(ActionDelegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public Widget asWidget() {
    return rootPanel;
  }

  @Override
  public AcceptsOneWidget getCommandsPanelContainer() {
    return commandsPanel;
  }

  @Override
  public AcceptsOneWidget getProcessesListContainer() {
    return processesListPanel;
  }

  @Override
  public AcceptsOneWidget getPreviewUrlsListContainer() {
    return previewUrlListPanel;
  }

  @Override
  public AcceptsOneWidget getPanelSelectorContainer() {
    return panelSelectorPanel;
  }

  @Override
  public AcceptsOneWidget getToolbarControllerContainer() {
    return toolbarControllerPanel;
  }

  @Override
  public void addButton(ToolbarButton button) {
    buttonsPanel.add(button);
  }

  interface CommandToolbarViewImplUiBinder extends UiBinder<Widget, CommandToolbarViewImpl> {}
}
