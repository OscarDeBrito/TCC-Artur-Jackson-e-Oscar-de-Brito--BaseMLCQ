public interface Channelizer extends ChannelHandler {

    /**
     * This method is called just after the {@code Channelizer} is initialized.
     */
    public void init(final ServerGremlinExecutor serverGremlinExecutor);

    /**
     * Create a message to send to seemingly dead clients to see if they respond back. The message sent will be
     * dependent on the implementation. For example, a websocket implementation would create a "ping" message.
     * This method will only be used if {@link #supportsIdleMonitor()} is {@code true}.
     */
    public default Object createIdleDetectionMessage() {
        return null;
    }

    /**
     * Determines if the channelizer supports a method for keeping the connection alive and auto-closing zombie
     * channels.
     */
    public default boolean supportsIdleMonitor() {
        return false;
    }
}
