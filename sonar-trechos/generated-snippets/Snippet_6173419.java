@Singleton
public class ShowPreferencesAction extends AbstractPerspectiveAction {

  private final PreferencesPresenter presenter;

  private final AppContext appContext;

  @Inject
  public ShowPreferencesAction(
      Resources resources, PreferencesPresenter presenter, AppContext appContext) {
    super(null, "Preferences", "Preferences", resources.preferences());
    this.presenter = presenter;
    this.appContext = appContext;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(ActionEvent e) {
    presenter.showPreferences();
  }

  @Override
  public void updateInPerspective(ActionEvent e) {
    e.getPresentation().setEnabledAndVisible(true);
  }
}
