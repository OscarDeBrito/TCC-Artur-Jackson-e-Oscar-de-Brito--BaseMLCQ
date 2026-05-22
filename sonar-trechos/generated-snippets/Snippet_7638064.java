public class Snippet__7638064 {

    private String getMessage(Exception _e) {
    		Throwable e = ExceptionUtil.getDeepestCause(_e);

    		// If value parse exception, do not append any additional information
    		if (e instanceof ValueParseException) {
    			String msg = e.getMessage();
    			if (StringUtil.hasText(msg)) {
    				return msg;
    			} else {
    				return "An error occurred: " + getSimpleError(e);
    			}
    		} else {
    			return ExceptionUtil.getMessage(e);
    		}
    	}

}
