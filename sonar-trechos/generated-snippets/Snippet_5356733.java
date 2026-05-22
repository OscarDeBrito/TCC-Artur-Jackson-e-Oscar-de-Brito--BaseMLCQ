public class SSLSocketChannelCommunicationsSession extends AbstractCommunicationsSession {

    private final SSLSocketChannel channel;
    private final SSLSocketChannelInput request;
    private final SSLSocketChannelOutput response;

    public SSLSocketChannelCommunicationsSession(final SSLSocketChannel channel) {
        super();
        request = new SSLSocketChannelInput(channel);
        response = new SSLSocketChannelOutput(channel);
        this.channel = channel;
    }

    @Override
    public SSLSocketChannelInput getInput() {
        return request;
    }

    @Override
    public SSLSocketChannelOutput getOutput() {
        return response;
    }

    @Override
    public void setTimeout(final int millis) throws IOException {
        channel.setTimeout(millis);
    }

    @Override
    public int getTimeout() throws IOException {
        return channel.getTimeout();
    }

    @Override
    public void close() throws IOException {
        IOException suppressed = null;

        try {
            request.consume();
        } catch (final IOException ioe) {
            suppressed = ioe;
        }

        try {
            channel.close();
        } catch (final IOException ioe) {
            if (suppressed != null) {
                ioe.addSuppressed(suppressed);
            }

            throw ioe;
        }

        if (suppressed != null) {
            throw suppressed;
        }
    }

    @Override
    public boolean isClosed() {
        return channel.isClosed();
    }

    @Override
    public boolean isDataAvailable() {
        try {
            return request.isDataAvailable();
        } catch (final Exception e) {
            return false;
        }
    }

    @Override
    public long getBytesWritten() {
        return response.getBytesWritten();
    }

    @Override
    public long getBytesRead() {
        return request.getBytesRead();
    }

    @Override
    public void interrupt() {
        channel.interrupt();
    }

    @Override
    public String toString() {
        return super.toString() + "[SSLSocketChannel=" + channel + "]";
    }
}
