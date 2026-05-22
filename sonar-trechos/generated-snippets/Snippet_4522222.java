public class Snippet__4522222 {

    public void put(String sid, UPnPEventListener listener) {
    		if (!sidListener.containsKey(sid)) {
    			Vector vec = new Vector();
    			vec.add(listener);
    			sidListener.put(sid, vec);
    		} else {
    			Vector vec = (Vector) sidListener.get(sid);
    			if (!vec.contains(listener)) {
    				vec.add(listener);
    			}
    		}
    	}

}
