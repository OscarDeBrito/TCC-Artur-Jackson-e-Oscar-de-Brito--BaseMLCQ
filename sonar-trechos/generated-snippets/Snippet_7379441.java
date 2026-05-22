public class Snippet__7379441 {

    public Snippet__7379441(String path) {

    			Assert.hasText(path, "Path must not be null/empty!");
    			this.path = path;

    			this.stringMatcher = null;
    			this.ignoreCase = null;
    			this.valueTransformer = NoOpPropertyValueTransformer.INSTANCE;
    		}

}
