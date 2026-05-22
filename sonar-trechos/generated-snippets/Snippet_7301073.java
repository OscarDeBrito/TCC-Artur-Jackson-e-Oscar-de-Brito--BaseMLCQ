public class Snippet__7301073 {

    public Snippet__7301073(final Workspace workspace) {
            Check.notNull(workspace, "workspace"); //$NON-NLS-1$

            this.workspace = workspace;

            workspace.getClient().getEventEngine().addNonFatalErrorListener(listener);
        }

}
