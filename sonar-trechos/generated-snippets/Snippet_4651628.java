public class Snippet__4651628 {

    @Nonnull
    	private E deserializeElement(@Nonnull byte[] bytes) {
    		try {
    			final int numPrefixBytes = groupPrefixBytes.length;
    			inputView.setBuffer(bytes, numPrefixBytes, bytes.length - numPrefixBytes);
    			return byteOrderProducingSerializer.deserialize(inputView);
    		} catch (IOException e) {
    			throw new FlinkRuntimeException("Error while deserializing the element.", e);
    		}
    	}

}
