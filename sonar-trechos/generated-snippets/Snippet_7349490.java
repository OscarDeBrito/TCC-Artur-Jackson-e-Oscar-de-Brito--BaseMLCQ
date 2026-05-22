private final class SwitchToWorkspaceAction extends Action {
        private final Workspace workspace;

        public SwitchToWorkspaceAction(final Workspace workspace) {
            super(workspace.getName(), IAction.AS_CHECK_BOX);
            this.workspace = workspace;
        }

        public Workspace getWorkspace() {
            return workspace;
        }

        @Override
        public void run() {
            switchWorkspace(workspace);
        }
    }
