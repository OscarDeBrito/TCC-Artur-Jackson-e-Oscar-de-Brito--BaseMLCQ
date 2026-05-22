public class Snippet__5525645 {

    protected T createEntityObject() {
    		try {
    			return tEntityClass.newInstance();
    		} catch (Throwable e) {
    			logger.error("Error instantiating entity class. tEntityClass="
    					+ tEntityClass.toString(), e);
    		}
    		return null;
    	}

}
