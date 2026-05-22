public class Snippet__7580167 {

    public ToStringCreator append(String fieldName, @Nullable Object value) {
    		printFieldSeparatorIfNecessary();
    		this.styler.styleField(this.buffer, fieldName, value);
    		return this;
    	}

}
