public class Snippet__5233290 {

    private List getCurrentBindings() throws NamingException {
    		List names;
    		try {
    			Registry registry = getRegistry();
    			names = Arrays.asList(registry.list());
    		} catch (RemoteException ex) {
    			names = Collections.EMPTY_LIST;
    		}
    		return names;
    	}

}
