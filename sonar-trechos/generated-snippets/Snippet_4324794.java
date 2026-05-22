public class Snippet__4324794 {

    private ThreadGroup createDaemonGroup() {
            try {
                ThreadGroup group = new ThreadGroup("derby.daemons");
                group.setDaemon(true);
                return group;
            } catch (SecurityException se) {
                // In case of a lacking privilege, issue a warning, return null and
                // let the daemon threads be created in the default thread group.
                // This can only happen if the current Derby thread is a part of
                // the root thread group "system".
                reportThread(se);
                return null;
            }
        }

}
