public class KdcUdpTransport extends KrbUdpTransport {
    private BlockingQueue<ByteBuffer> bufferQueue = new ArrayBlockingQueue<>(2);

    public KdcUdpTransport(DatagramChannel channel, InetSocketAddress remoteAddress) throws IOException {
        super(remoteAddress);
        setChannel(channel);
    }

    @Override
    public synchronized ByteBuffer receiveMessage() throws IOException {
        long timeout = 1000; // TODO: configurable or option
        ByteBuffer message;
        try {
            message = bufferQueue.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
        return message;
    }

    protected synchronized void onRecvMessage(ByteBuffer message) {
        if (message != null) {
            bufferQueue.add(message);
        }
    }
}
