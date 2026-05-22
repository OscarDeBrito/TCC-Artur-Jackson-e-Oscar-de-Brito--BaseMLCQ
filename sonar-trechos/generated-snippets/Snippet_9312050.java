public class Snippet__9312050 {

    private static Void checkPermission() {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null)
                sm.checkPermission(new RuntimePermission("selectorProvider"));
            return null;
        }

}
