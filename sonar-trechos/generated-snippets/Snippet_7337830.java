public class OpenBuildDefinitionVNextAction extends BuildDefinitionVNextAction {
    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void doRun(final IAction action) {
        final DefinitionReference buildDefinition = getSelectedBuildDefinition();

        if (buildDefinition != null) {
            new OpenBuildDefinitionVNextTask(getShell(), getConnection(), buildDefinition).run();
            ;
        }
    }
}
