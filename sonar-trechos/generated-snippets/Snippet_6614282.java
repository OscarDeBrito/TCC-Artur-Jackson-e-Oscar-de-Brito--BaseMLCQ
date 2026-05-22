public class Snippet__6614282 {

    private int countN4JSResourcesBuiltIn(ResourceSet resSet) {
    		int n = 0;
    		for (Resource res : resSet.getResources()) {
    			if (isBuiltInResource(res)) {
    				n++;
    			}
    		}
    		return n;
    	}

}
