public class Snippet__3799104 {

    public static void rmpath(String path) {
            LOG.debug("Removing path " + path);
            boolean succ = (new File(path)).delete();
            if (!succ) {
                throw new RuntimeException("Failed to delete " + path);
            }
        }

}
