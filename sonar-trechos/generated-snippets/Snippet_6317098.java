public class CloudPayloadGZipEncoder implements CloudPayloadEncoder {

    private final CloudPayloadEncoder decorated;

    public CloudPayloadGZipEncoder(CloudPayloadEncoder decorated) {
        this.decorated = decorated;
    }

    @Override
    public byte[] getBytes() throws IOException {
        byte[] source = this.decorated.getBytes();
        byte[] compressed = GZipUtil.compress(source);

        // Return gzip compressed data only if shorter than uncompressed one
        return compressed.length < source.length ? compressed : source;
    }
}
