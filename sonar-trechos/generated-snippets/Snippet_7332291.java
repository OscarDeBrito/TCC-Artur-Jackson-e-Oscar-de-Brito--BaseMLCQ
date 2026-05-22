public interface ITeamExplorerNavigationLink {
    public boolean isEnabled(final TeamExplorerContext context);

    public boolean isVisible(final TeamExplorerContext context);

    public void clicked(
        final Shell shell,
        final TeamExplorerContext context,
        final TeamExplorerNavigator navigator,
        final TeamExplorerNavigationItemConfig parentNavigationItem);
}
