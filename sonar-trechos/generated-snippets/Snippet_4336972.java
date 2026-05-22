public class Snippet__4336972 {

    public PersistentService getServiceType(Object serviceModule) {
    		TopService ts = findTopService(serviceModule);

    		if (ts == null)
    			return null;

    		return ts.getServiceType();
    	}

}
