public abstract class AsynchronousFileIOChannel<T, R extends IORequest> extends AbstractFileIOChannel {

	private final Object listenerLock = new Object();

	/**
	 * The lock that is used during closing to synchronize the thread that waits for all
	 * requests to be handled with the asynchronous I/O thread.
	 */
	protected final Object closeLock = new Object();

	/** A request queue for submitting asynchronous requests to the corresponding IO worker thread. */
	protected final RequestQueue<R> requestQueue;

	/** An atomic integer that counts the number of requests that we still wait for to return. */
	protected final AtomicInteger requestsNotReturned = new AtomicInteger(0);
	
	/** Handler for completed requests */
	protected final RequestDoneCallback<T> resultHandler;

	/** An exception that was encountered by the asynchronous request handling thread. */
	protected volatile IOException exception;

	/** Flag marking this channel as closed */
	protected volatile boolean closed;

	private NotificationListener allRequestsProcessedListener;

	// --------------------------------------------------------------------------------------------

	/**
	 * Creates a new channel access to the path indicated by the given ID. The channel accepts buffers to be
	 * read/written and hands them to the asynchronous I/O thread. After being processed, the buffers
	 * are returned by adding the to the given queue.
	 *
	 * @param channelID    The id describing the path of the file that the channel accessed.
	 * @param requestQueue The queue that this channel hands its IO requests to.
	 * @param callback     The callback to be invoked when a request is done.
	 * @param writeEnabled Flag describing whether the channel should be opened in read/write mode, rather
	 *                     than in read-only mode.
	 * @throws IOException Thrown, if the channel could no be opened.
	 */
	protected AsynchronousFileIOChannel(FileIOChannel.ID channelID, RequestQueue<R> requestQueue, 
			RequestDoneCallback<T> callback, boolean writeEnabled) throws IOException
	{
		super(channelID, writeEnabled);

		this.requestQueue = checkNotNull(requestQueue);
		this.resultHandler = checkNotNull(callback);
	}

	// --------------------------------------------------------------------------------------------

	@Override
	public boolean isClosed() {
		return this.closed;
	}

	/**
	 * Closes the channel and waits until all pending asynchronous requests are processed. The
	 * underlying <code>FileChannel</code> is closed even if an exception interrupts the closing.
	 *
	 * <p> <strong>Important:</strong> the {@link #isClosed()} method returns <code>true</code>
	 * immediately after this method has been called even when there are outstanding requests.
	 *
	 * @throws IOException Thrown, if an I/O exception occurred while waiting for the buffers, or if
	 *                     the closing was interrupted.
	 */
	@Override
	public void close() throws IOException {
		// atomically set the close flag
		synchronized (this.closeLock) {
			if (this.closed) {
				return;
			}
			this.closed = true;

			try {
				// wait until as many buffers have been returned as were written
				// only then is everything guaranteed to be consistent.
				while (this.requestsNotReturned.get() > 0) {
					try {
						// we add a timeout here, because it is not guaranteed that the
						// decrementing during buffer return and the check here are deadlock free.
						// the deadlock situation is however unlikely and caught by the timeout
						this.closeLock.wait(1000);
						checkErroneous();
					}
					catch (InterruptedException iex) {
						throw new IOException("Closing of asynchronous file channel was interrupted.");
					}
				}

				// Additional check because we might have skipped the while loop
				checkErroneous();
			}
			finally {
				// close the file
				if (this.fileChannel.isOpen()) {
					this.fileChannel.close();
				}
			}
		}
	}
	
	/**
	 * This method waits for all pending asynchronous requests to return. When the
	 * last request has returned, the channel is closed and deleted.
	 * <p>
	 * Even if an exception interrupts the closing, such that not all request are handled,
	 * the underlying <tt>FileChannel</tt> is closed and deleted.
	 *
	 * @throws IOException Thrown, if an I/O exception occurred while waiting for the buffers, or if the closing was interrupted.
	 */
	@Override
	public void closeAndDelete() throws IOException {
		try {
			close();
		}
		finally {
			deleteChannel();
		}
	}

	/**
	 * Checks the exception state of this channel. The channel is erroneous, if one of its requests could not
	 * be processed correctly.
	 *
	 * @throws IOException Thrown, if the channel is erroneous. The thrown exception contains the original exception
	 *                     that defined the erroneous state as its cause.
	 */
	public final void checkErroneous() throws IOException {
		if (this.exception != null) {
			throw this.exception;
		}
	}

	/**
	 * Handles a processed <tt>Buffer</tt>. This method is invoked by the
	 * asynchronous IO worker threads upon completion of the IO request with the
	 * provided buffer and/or an exception that occurred while processing the request
	 * for that buffer.
	 *
	 * @param buffer The buffer to be processed.
	 * @param ex     The exception that occurred in the I/O threads when processing the buffer's request.
	 */
	final protected void handleProcessedBuffer(T buffer, IOException ex) {
		if (buffer == null) {
			return;
		}

		// even if the callbacks throw an error, we need to maintain our bookkeeping
		try {
			if (ex != null && this.exception == null) {
				this.exception = ex;
				this.resultHandler.requestFailed(buffer, ex);
			}
			else {
				this.resultHandler.requestSuccessful(buffer);
			}
		}
		finally {
			NotificationListener listener = null;

			// Decrement the number of outstanding requests. If we are currently closing, notify the
			// waiters. If there is a listener, notify her as well.
			synchronized (this.closeLock) {
				if (this.requestsNotReturned.decrementAndGet() == 0) {
					if (this.closed) {
						this.closeLock.notifyAll();
					}

					synchronized (listenerLock) {
						listener = allRequestsProcessedListener;
						allRequestsProcessedListener = null;
					}
				}
			}

			if (listener != null) {
				listener.onNotification();
			}
		}
	}

	final protected void addRequest(R request) throws IOException {
		// check the error state of this channel
		checkErroneous();

		// write the current buffer and get the next one
		this.requestsNotReturned.incrementAndGet();

		if (this.closed || this.requestQueue.isClosed()) {
			// if we found ourselves closed after the counter increment,
			// decrement the counter again and do not forward the request
			this.requestsNotReturned.decrementAndGet();

			final NotificationListener listener;

			synchronized (listenerLock) {
				listener = allRequestsProcessedListener;
				allRequestsProcessedListener = null;
			}

			if (listener != null) {
				listener.onNotification();
			}

			throw new IOException("I/O channel already closed. Could not fulfill: " + request);
		}

		this.requestQueue.add(request);
	}

	/**
	 * Registers a listener to be notified when all outstanding requests have been processed.
	 *
	 * <p> New requests can arrive right after the listener got notified. Therefore, it is not safe
	 * to assume that the number of outstanding requests is still zero after a notification unless
	 * there was a close right before the listener got called.
	 *
	 * <p> Returns <code>true</code>, if the registration was successful. A registration can fail,
	 * if there are no outstanding requests when trying to register a listener.
	 */
	protected boolean registerAllRequestsProcessedListener(NotificationListener listener) throws IOException {
		checkNotNull(listener);

		synchronized (listenerLock) {
			if (allRequestsProcessedListener == null) {
				// There was a race with the processing of the last outstanding request
				if (requestsNotReturned.get() == 0) {
					return false;
				}

				allRequestsProcessedListener = listener;

				return true;
			}
		}

		throw new IllegalStateException("Already subscribed.");
	}
}
