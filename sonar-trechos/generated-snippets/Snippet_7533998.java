public class Snippet__7533998 {

    public Object aggregate(Collection<Object> results) {
    		for (Object o : results) {
    			if (o instanceof MessagingException) {
    				return new ConsolidatedResultsException(results);
    			}
    		}
    		return results;
    	}

}
