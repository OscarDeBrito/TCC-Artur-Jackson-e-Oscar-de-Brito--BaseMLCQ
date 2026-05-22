public class Snippet__7538101 {

    @Override
    	public <T> List<T> search(Name base, String filter, int searchScope, ContextMapper<T> mapper) {
    		return search(base, filter, searchScope, ALL_ATTRIBUTES, mapper);
    	}

}
