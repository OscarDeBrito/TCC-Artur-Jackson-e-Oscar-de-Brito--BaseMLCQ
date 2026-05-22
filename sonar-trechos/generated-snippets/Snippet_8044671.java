public class Snippet__8044671 {

    protected String getArrayComponentTypeSimpleName(String typeName) {
    		int idx = typeName.indexOf('[');
    		if (idx != -1) {
    			return getSimpleName(typeName.substring(0, idx));
    		}
    		return null;
    	}

}
