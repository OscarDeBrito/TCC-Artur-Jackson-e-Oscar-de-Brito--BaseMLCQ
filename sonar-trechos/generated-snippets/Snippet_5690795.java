public class Snippet__5690795 {

    public int delete(IdEntity object) throws CreateException {
    		if (object == null) {
    			throw new CreateException("Cannot delete null object");
    		}
    		return delete(object.getClass(), object.getId());
    	}

}
