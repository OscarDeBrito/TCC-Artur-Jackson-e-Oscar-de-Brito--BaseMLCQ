public class StringBlob {

    private byte[] rawBlob;
    private String string;

    /**
     * Construct string blob from compressed byte array
     *
     * @param byteArray the byte array
     */
    public StringBlob(byte[] byteArray) {
        this.rawBlob = ByteArrayUtils.weakIntern(byteArray);
    }

    /**
     * Construct StringBlob with uncompressed string
     *
     * @param inputString the string
     */
    public StringBlob(String inputString) {
        this.string = StringUtils.intern(inputString);
        this.rawBlob = null;
    }

    /**
     * Set string
     *
     * @param str the string
     */
    public void setString(String str) {
        this.string = StringUtils.intern(str);
        this.rawBlob = null;
    }

    /**
     * Get uncompressed string
     *
     * @return uncompressed string
     */
    public String getString() {
        if (string != null) {
            return string;
        }
        if (rawBlob == null) {
            return null;
        }
        try {
            DataInputStream dais = new DataInputStream(new ByteArrayInputStream(rawBlob));
            CompressionCodec codec = CodecFactory.getDeCompressionCodec(dais);
            if (codec != null) {
                string = StringUtils.intern(codec.decompressToString(dais));
            }
            else {
                string = StringUtils.intern((new String(rawBlob, CodecFactory.UTF_8_ENCODING)));
            }
            dais.close();

        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        rawBlob = null;
        return string;
    }

    /**
     * Get raw blob
     *
     * @return raw blob
     */
    public byte[] getRawBlob() {
        if (rawBlob != null) {
            return rawBlob;
        }
        if (string == null) {
            return null;
        }
        if (CodecFactory.isCompressionEnabled()) {
            byte[] bytes = CodecFactory.getHeaderBytes();
            try {
                rawBlob = ByteArrayUtils.weakIntern(CodecFactory.getCompressionCodec().compressString(bytes, string));
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            rawBlob = ByteArrayUtils.weakIntern(string.getBytes(Charsets.UTF_8));
        }
        return rawBlob;
    }

}
