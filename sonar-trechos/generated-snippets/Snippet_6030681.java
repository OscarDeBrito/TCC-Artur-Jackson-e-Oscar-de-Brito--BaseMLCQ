public class Snippet__6030681 {

    public static NetworkConfig createStandardFromStream(InputStream inStream) {
    		LOGGER.config("Creating standard network configuration properties from stream");
    		standard = new NetworkConfig();
    		try {
    			standard.load(inStream);
    		} catch (IOException e) {
    			LOGGER.log(Level.WARNING, "cannot load properties from stream: {0}", e.getMessage());
    		}
    		return standard;
    	}

}
