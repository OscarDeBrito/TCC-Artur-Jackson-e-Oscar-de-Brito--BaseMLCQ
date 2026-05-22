public class Snippet__7610926 {

    public static String getClassVersion(Class clazz) {
    		String version = "unkown";
    		if (clazz.getPackage().getImplementationVersion() != null) {
    			version = clazz.getPackage().getImplementationVersion();
    		}
    		return version;
    	}

}
