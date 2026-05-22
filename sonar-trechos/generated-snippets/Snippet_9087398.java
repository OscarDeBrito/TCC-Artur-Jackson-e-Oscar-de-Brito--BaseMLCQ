public class Snippet__9087398 {

    private abstract static class RSocketReadWriteConnection extends DelegateReadWriteRConnection {
            private Socket socket;
            private SocketChannel channel;
            protected final RSocketConnection thisBase;

            protected RSocketReadWriteConnection(RSocketConnection base) {
                super(base, 0);
                this.thisBase = base;
            }

            protected void openStreams(SocketChannel socketArg) throws IOException {
                channel = socketArg;
                socket = socketArg.socket();
                if (thisBase.isBlocking()) {
                    channel.configureBlocking(true);
                    // Java (int) timeouts do not meet the POSIX standard of 31 days
                    long millisTimeout = ((long) thisBase.timeout) * 1000;
                    if (millisTimeout > Integer.MAX_VALUE) {
                        millisTimeout = Integer.MAX_VALUE;
                    }
                    socket.setSoTimeout((int) millisTimeout);
                } else {
                    channel.configureBlocking(false);
                }
            }

            @Override
            public ByteChannel getChannel() {
                return channel;
            }

            @Override
            public boolean isSeekable() {
                return false;
            }
        }

}
