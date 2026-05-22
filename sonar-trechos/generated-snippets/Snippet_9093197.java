static class PipeReadRConnection extends DelegateReadRConnection {
        private final ByteChannel channel;

        protected PipeReadRConnection(BaseRConnection base, String command) throws IOException {
            super(base);
            Process p = PipeConnections.executeAndJoin(command);
            channel = ConnectionSupport.newChannel(p.getInputStream());
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
