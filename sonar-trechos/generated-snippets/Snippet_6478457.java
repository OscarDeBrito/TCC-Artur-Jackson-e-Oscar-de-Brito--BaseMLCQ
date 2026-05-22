public class Snippet__6478457 {

    @Override
    	protected byte[] getVariableHeader() throws MqttException {
    		try {
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			DataOutputStream outputStream = new DataOutputStream(baos);

    			// Encode the Message ID
    			outputStream.writeShort(msgId);


    			// Write Identifier / Value Fields
    			byte[] identifierValueFieldsByteArray = this.properties.encodeProperties();
    			outputStream.write(identifierValueFieldsByteArray);
    			outputStream.flush();
    			return baos.toByteArray();
    		} catch (IOException ioe) {
    			throw new MqttException(ioe);
    		}
    	}

}
