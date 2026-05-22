public class Snippet__8348686 {

    private static final void getPathComponents(String path, ArrayList<String> list) {
    		if (path != null) {
    			StringTokenizer tok = new StringTokenizer(path, File.pathSeparator);

    			while (tok.hasMoreTokens()) {
    				String name = tok.nextToken();
    				File file = new File(name);

    				if (file.exists())
    					list.add(name);
    			}
    		}
    	}

}
