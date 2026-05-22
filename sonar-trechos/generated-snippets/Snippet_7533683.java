public class Snippet__7533683 {

    protected void checkClosure(int bite) throws IOException {
    		if (bite < 0) {
    			logger.debug("Socket closed during message assembly");
    			throw new IOException("Socket closed during message assembly");
    		}
    	}

}
