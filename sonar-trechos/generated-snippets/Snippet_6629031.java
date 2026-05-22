public class Snippet__6629031 {

    public OGCLineString exteriorRing() {
    		if (polygon.isEmpty())
    			return new OGCLinearRing((Polygon) polygon.createInstance(), 0,
    					esriSR, true);
    		return new OGCLinearRing(polygon, 0, esriSR, true);
    	}

}
