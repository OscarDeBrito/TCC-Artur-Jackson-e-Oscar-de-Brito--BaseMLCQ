public class Snippet__6627471 {

    public int findWkid(String unit) {
    		if (!wkidLookup.containsKey(unit)) {
    			throw new IllegalArgumentException();
    		}
    		return wkidLookup.get(unit);
    	}

}
