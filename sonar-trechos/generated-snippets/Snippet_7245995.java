public class Snippet__7245995 {

    public void notifyActivated() {
            resetResults();
        
            resetter = Lookup.getDefault().lookup(ObjectsResetter.class);
            resetter.controller = this;
        
            if (getSession().inProgress() && !currentMode.currentSettingsValid()) {
                final String oldMode = currentMode.getName();
                final String newMode = allClassesMode.getName();
                setMode(allClassesMode);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ProfilerDialogs.displayInfo(Bundle.MethodsFeature_modeReset(
                                                    oldMode, newMode));
                    }
                });
            }
        }

}
