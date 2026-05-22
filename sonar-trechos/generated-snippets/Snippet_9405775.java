public class Snippet__9405775 {

    protected Container getGlobalCurrentFocusCycleRoot()
            throws SecurityException
        {
            synchronized (KeyboardFocusManager.class) {
                checkKFMSecurity();
                return currentFocusCycleRoot;
            }
        }

}
