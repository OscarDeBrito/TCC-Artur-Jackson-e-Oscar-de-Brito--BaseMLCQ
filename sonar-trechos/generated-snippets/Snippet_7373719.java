public class Snippet__7373719 {

    private void notifyConflict() {
            final Shell parentShell = ShellUtils.getWorkbenchShell();

            UIHelpers.runOnUIThread(true, new Runnable() {
                @Override
                public void run() {
                    MessageDialog.openWarning(
                        parentShell,
                        Messages.getString("EclipseConnectionConflictHandler.ConnectionExistsDialogTitle"), //$NON-NLS-1$
                        Messages.getString("EclipseConnectionConflictHandler.ConnectionExistsDialogText")); //$NON-NLS-1$
                }
            });
        }

}
