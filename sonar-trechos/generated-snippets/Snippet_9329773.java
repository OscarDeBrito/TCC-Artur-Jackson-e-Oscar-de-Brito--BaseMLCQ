public class Snippet__9329773 {

    public Snippet__9329773(InetSocketAddress addr, HttpClientImpl client) {
            super(addr, client);
            try {
                this.chan = SocketChannel.open();
                chan.configureBlocking(false);
                trySetReceiveBufferSize(client.getReceiveBufferSize());
                if (debug.on()) {
                    int bufsize = getInitialBufferSize();
                    debug.log("Initial receive buffer size is: %d", bufsize);
                }
                chan.setOption(StandardSocketOptions.TCP_NODELAY, true);
                // wrap the channel in a Tube for async reading and writing
                tube = new SocketTube(client(), chan, Utils::getBuffer);
            } catch (IOException e) {
                throw new InternalError(e);
            }
        }

}
