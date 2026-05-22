public class Snippet__6468434 {

    private static final class JarFilter implements FilenameFilter {

    		JarFilter() {
    			super();
    		}

    		@Override
    		public boolean accept(File dir, String name) {
    			// accept JAR files *.jar only
    			return name.regionMatches(true, name.length() - 4, ".jar", 0, 4); //$NON-NLS-1$
    		}

    	}

}
