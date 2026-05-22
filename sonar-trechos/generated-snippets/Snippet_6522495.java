public class ByteArrayAudioStream extends FixedLengthAudioStream {

    private byte[] bytes;
    private AudioFormat format;
    private ByteArrayInputStream stream;

    public ByteArrayAudioStream(byte[] bytes, AudioFormat format) {
        this.bytes = bytes;
        this.format = format;
        this.stream = new ByteArrayInputStream(bytes);
    }

    @Override
    public AudioFormat getFormat() {
        return format;
    }

    @Override
    public int read() throws IOException {
        return stream.read();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

    @Override
    public long length() {
        return bytes.length;
    }

    @Override
    public InputStream getClonedStream() {
        return new ByteArrayAudioStream(bytes, format);
    }

}
