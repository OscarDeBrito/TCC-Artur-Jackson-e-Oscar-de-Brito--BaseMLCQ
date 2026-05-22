public class Snippet__5302732 {

    @Override
        public void valueChanged(Value val) {
        	if (val != null && val.matches("DataCacheTimeout")) {
        		_cacheTimeout = Integer.MIN_VALUE;
        	}
        }

}
