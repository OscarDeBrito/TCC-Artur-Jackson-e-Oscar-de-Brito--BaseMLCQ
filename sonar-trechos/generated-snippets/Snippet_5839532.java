public class Snippet__5839532 {

    public static String adjustRelativePath(String path) throws FileNotFoundException {
            // Empty or null path can't point to anything useful. The assumption is
            // that the value is deliberately empty / null so leave it that way.
            if (path == null || path.length() == 0) {
                return path;
            }
            String newPath = path;
            File f = new File(newPath);
            if ( !f.isAbsolute()) {
                newPath = System.getProperty(Constants.CATALINA_BASE_PROP) + File.separator + newPath;
                f = new File(newPath);
            }
            if (!f.exists()) {
                throw new FileNotFoundException(sm.getString("sslHostConfig.fileNotFound", newPath));
            }
            return newPath;
        }

}
