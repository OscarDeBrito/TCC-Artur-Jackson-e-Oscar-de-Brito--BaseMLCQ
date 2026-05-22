public interface Connection extends ShutdownNotifier, Closeable { // rename to AMQPConnection later, this is a temporary name
    /**
     * Retrieve the host.
     * @return the hostname of the peer we're connected to.
     */
    InetAddress getAddress();

    /**
     * Retrieve the port number.
     * @return the port number of the peer we're connected to.
     */

    int getPort();

    /**
     * Get the negotiated maximum channel number. Usable channel
     * numbers range from 1 to this number, inclusive.
     *
     * @return the maximum channel number permitted for this connection.
     */
    int getChannelMax();

    /**
     * Get the negotiated maximum frame size.
     *
     * @return the maximum frame size, in octets; zero if unlimited
     */
    int getFrameMax();

    /**
     * Get the negotiated heartbeat interval.
     *
     * @return the heartbeat interval, in seconds; zero if none
     */
    int getHeartbeat();

    /**
     * Get a copy of the map of client properties sent to the server
     *
     * @return a copy of the map of client properties
     */
    Map<String, Object> getClientProperties();

    /**
     * Returns client-provided connection name, if any. Note that the value
     * returned does not uniquely identify a connection and cannot be used
     * as a connection identifier in HTTP API requests.
     *
     *
     *
     * @return client-provided connection name, if any
     * @see ConnectionFactory#newConnection(Address[], String)
     * @see ConnectionFactory#newConnection(ExecutorService, Address[], String)
     */
    String getClientProvidedName();

    /**
     * Retrieve the server properties.
     * @return a map of the server properties. This typically includes the product name and version of the server.
     */
    Map<String, Object> getServerProperties();

    /**
     * Create a new channel, using an internally allocated channel number.
     * If <a href="https://www.rabbitmq.com/api-guide.html#recovery">automatic connection recovery</a>
     * is enabled, the channel returned by this method will be {@link Recoverable}.
     * <p>
     * Use {@link #openChannel()} if you want to use an {@link Optional} to deal
     * with a {@null} value.
     *
     * @return a new channel descriptor, or null if none is available
     * @throws IOException if an I/O problem is encountered
     */
    Channel createChannel() throws IOException;

    /**
     * Create a new channel, using the specified channel number if possible.
     * <p>
     * Use {@link #openChannel(int)} if you want to use an {@link Optional} to deal
     * with a {@null} value.
     *
     * @param channelNumber the channel number to allocate
     * @return a new channel descriptor, or null if this channel number is already in use
     * @throws IOException if an I/O problem is encountered
     */
    Channel createChannel(int channelNumber) throws IOException;

    /**
     * Create a new channel wrapped in an {@link Optional}.
     * The channel number is allocated internally.
     * <p>
     * If <a href="https://www.rabbitmq.com/api-guide.html#recovery">automatic connection recovery</a>
     * is enabled, the channel returned by this method will be {@link Recoverable}.
     * <p>
     * Use {@link #createChannel()} to return directly a {@link Channel} or {@code null}.
     *
     * @return an {@link Optional} containing the channel;
     * never {@code null} but potentially empty if no channel is available
     * @throws IOException if an I/O problem is encountered
     * @see #createChannel()
     * @since 5.6.0
     */
    default Optional<Channel> openChannel() throws IOException {
        return Optional.ofNullable(createChannel());
    }

    /**
     * Create a new channel, using the specified channel number if possible.
     * <p>
     * Use {@link #createChannel(int)} to return directly a {@link Channel} or {@code null}.
     *
     * @param channelNumber the channel number to allocate
     * @return an {@link Optional} containing the channel,
     * never {@code null} but potentially empty if this channel number is already in use
     * @throws IOException if an I/O problem is encountered
     * @see #createChannel(int)
     * @since 5.6.0
     */
    default Optional<Channel> openChannel(int channelNumber) throws IOException {
        return Optional.ofNullable(createChannel(channelNumber));
    }

    /**
     * Close this connection and all its channels
     * with the {@link com.rabbitmq.client.AMQP#REPLY_SUCCESS} close code
     * and message 'OK'.
     *
     * Waits for all the close operations to complete.
     *
     * @throws IOException if an I/O problem is encountered
     */
    @Override
    void close() throws IOException;

    /**
     * Close this connection and all its channels.
     *
     * Waits for all the close operations to complete.
     *
     * @param closeCode the close code (See under "Reply Codes" in the AMQP specification)
     * @param closeMessage a message indicating the reason for closing the connection
     * @throws IOException if an I/O problem is encountered
     */
    void close(int closeCode, String closeMessage) throws IOException;

    /**
     * Close this connection and all its channels
     * with the {@link com.rabbitmq.client.AMQP#REPLY_SUCCESS} close code
     * and message 'OK'.
     * 
     * This method behaves in a similar way as {@link #close()}, with the only difference
     * that it waits with a provided timeout for all the close operations to
     * complete. When timeout is reached the socket is forced to close.
     * 
     * @param timeout timeout (in milliseconds) for completing all the close-related
     * operations, use -1 for infinity
     * @throws IOException if an I/O problem is encountered
     */
    void close(int timeout) throws IOException;
    
    /**
     * Close this connection and all its channels.
     *
     * Waits with the given timeout for all the close operations to complete.
     * When timeout is reached the socket is forced to close.
     * 
     * @param closeCode the close code (See under "Reply Codes" in the AMQP specification)
     * @param closeMessage a message indicating the reason for closing the connection
     * @param timeout timeout (in milliseconds) for completing all the close-related
     * operations, use -1 for infinity
     * @throws IOException if an I/O problem is encountered
     */
    void close(int closeCode, String closeMessage, int timeout) throws IOException;

    /**
     * Abort this connection and all its channels
     * with the {@link com.rabbitmq.client.AMQP#REPLY_SUCCESS} close code
     * and message 'OK'.
     *
     * Forces the connection to close.
     * Any encountered exceptions in the close operations are silently discarded.
     */
    void abort();
    
    /**
     * Abort this connection and all its channels.
     *
     * Forces the connection to close and waits for all the close operations to complete.
     * Any encountered exceptions in the close operations are silently discarded.
     * 
     * @param closeCode the close code (See under "Reply Codes" in the AMQP specification)
     * @param closeMessage a message indicating the reason for closing the connection
     */
    void abort(int closeCode, String closeMessage);
    
    /**
     * Abort this connection and all its channels
     * with the {@link com.rabbitmq.client.AMQP#REPLY_SUCCESS} close code
     * and message 'OK'.
     *
     * This method behaves in a similar way as {@link #abort()}, with the only difference
     * that it waits with a provided timeout for all the close operations to
     * complete. When timeout is reached the socket is forced to close.
     *
     * @param timeout timeout (in milliseconds) for completing all the close-related
     * operations, use -1 for infinity
     */
    void abort(int timeout);

    /**
     * Abort this connection and all its channels.
     *
     * Forces the connection to close and waits with the given timeout
     * for all the close operations to complete. When timeout is reached
     * the socket is forced to close.
     * Any encountered exceptions in the close operations are silently discarded.
     *
     * @param closeCode the close code (See under "Reply Codes" in the AMQP specification)
     * @param closeMessage a message indicating the reason for closing the connection
     * @param timeout timeout (in milliseconds) for completing all the close-related
     * operations, use -1 for infinity
     */
    void abort(int closeCode, String closeMessage, int timeout);

    /**
     * Add a {@link BlockedListener}.
     * @param listener the listener to add
     */
    void addBlockedListener(BlockedListener listener);

    /**
     * Add a lambda-based {@link BlockedListener}.
     * @see BlockedListener
     * @see BlockedCallback
     * @see UnblockedCallback
     * @param blockedCallback the callback when the connection is blocked
     * @param unblockedCallback the callback when the connection is unblocked
     * @return the listener that wraps the callback
     */
    BlockedListener addBlockedListener(BlockedCallback blockedCallback, UnblockedCallback unblockedCallback);

    /**
     * Remove a {@link BlockedListener}.
     * @param listener the listener to remove
     * @return <code><b>true</b></code> if the listener was found and removed,
     * <code><b>false</b></code> otherwise
     */
    boolean removeBlockedListener(BlockedListener listener);

    /**
     * Remove all {@link BlockedListener}s.
     */
    void clearBlockedListeners();

    /**
     * Get the exception handler.
     *
     * @see com.rabbitmq.client.ExceptionHandler
     */
    ExceptionHandler getExceptionHandler();

    /**
     * Returns a unique ID for this connection.
     *
     * This ID must be unique, otherwise some services
     * like the metrics collector won't work properly.
     * This ID doesn't have to be provided by the client,
     * services that require it will be assigned automatically
     * if not set.
     *
     * @return unique ID for this connection.
     */
    String getId();

    /**
     * Sets a unique ID for this connection.
     *
     * This ID must be unique, otherwise some services
     * like the metrics collector won't work properly.
     * This ID doesn't have to be provided by the client,
     * services that require it will be assigned automatically
     * if not set.
     */
    void setId(String id);
}
