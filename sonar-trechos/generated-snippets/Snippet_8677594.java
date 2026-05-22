public interface IpcServerEndpoint extends Closeable {
    /**
     * Accepts client IPC connection. After client connection is accepted, it can be used
     * for IPC. This method will block until client connects to IPC server endpoint.
     *
     * @return Accepted client connection.
     * @throws IgniteCheckedException If accept failed and the endpoint is not usable anymore.
     */
    public IpcEndpoint accept() throws IgniteCheckedException;

    /**
     * Starts configured endpoint implementation.
     *
     * @throws IgniteCheckedException If failed to start server endpoint.
     */
    public void start() throws IgniteCheckedException;

    /**
     * Closes server IPC. After IPC is closed, no further operations can be performed on this
     * object.
     */
    @Override public void close();

    /**
     * Gets port endpoint is bound to.
     * Endpoints who does not bind to any port should return -1.
     *
     * @return Port number.
     */
    public int getPort();

    /**
     * Gets host endpoint is bound to.
     * Endpoints who does not bind to any port should return {@code null}.
     *
     * @return Host.
     */
    @Nullable public String getHost();

    /**
     * Indicates if this endpoint is a management endpoint.
     *
     * @return {@code true} if it's a management endpoint.
     */
    public boolean isManagement();
}
