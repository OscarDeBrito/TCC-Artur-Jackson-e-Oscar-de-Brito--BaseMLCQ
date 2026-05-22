public class Snippet__6615560 {

    public static String
    toString(byte [] b) {
    	ByteArrayOutputStream os = new ByteArrayOutputStream();

    	for (int i = 0; i < b.length; i++) {
    		short value = (short) (b[i] & 0xFF);
    		byte high = (byte) (value >> 4);
    		byte low = (byte) (value & 0xF);
    		os.write(Base16.charAt(high));
    		os.write(Base16.charAt(low));
    	}
    	return new String(os.toByteArray());
    }

}
