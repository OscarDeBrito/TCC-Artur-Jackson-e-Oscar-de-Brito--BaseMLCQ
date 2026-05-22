public class Snippet__7306360 {

    private Object readTypeToken(TypeToken<?> token, HttpInputMessage inputMessage) throws IOException {
    		Reader json = new InputStreamReader(inputMessage.getBody(), getCharset(inputMessage.getHeaders()));
    		try {
    			return this.gson.fromJson(json, token.getType());
    		}
    		catch (JsonParseException ex) {
    			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
    		}
    	}

}
