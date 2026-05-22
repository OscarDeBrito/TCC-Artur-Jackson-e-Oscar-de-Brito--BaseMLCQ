public class Snippet__6475184 {

    void setValue(String value) throws IllegalArgumentException {
    		if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) {
    			formatTimestamp = true;
    		} else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) {
    			formatTimestamp = false;
    		} else {
    			throw new IllegalArgumentException("The value \""+value+"\" specified for format timestamp is not valid, must be true or false");
    		}
    	}

}
