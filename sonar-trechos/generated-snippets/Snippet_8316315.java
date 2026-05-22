public class VcsPullRequestsController extends TabControllerImpl<VcsPullRequestsModel> {
    private static final String EVENT_NAME = "Pull Requests";

    public VcsPullRequestsController(final @NotNull Project project) {
        super(new TabImpl(new VcsPullRequestsForm(), EVENT_NAME), new VcsPullRequestsModel(project),
                new ServerEvent[]{ServerEvent.PULL_REQUESTS_CHANGED});
    }

    @Override
    protected void performAction(final ActionEvent e) {
        if (VcsPullRequestsForm.CMD_OPEN_SELECTED_ITEM_IN_BROWSER.equals(e.getActionCommand())) {
            //pop up menu - open PR link in web
            model.openSelectedItemsLink();
        } else if (VcsPullRequestsForm.CMD_ABANDON_SELECTED_PR.equals(e.getActionCommand())) {
            //pop up menu - abandon PR
            model.abandonSelectedPullRequest();
        } else {
            super.performAction(e);
        }
    }
}
