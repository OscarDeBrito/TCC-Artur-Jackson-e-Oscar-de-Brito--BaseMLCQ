public class RequestHandlerRetryAdvice extends AbstractRequestHandlerAdvice
		implements RetryListener {

	private RetryTemplate retryTemplate = new RetryTemplate();

	private RecoveryCallback<Object> recoveryCallback;

	private static final ThreadLocal<Message<?>> messageHolder = new ThreadLocal<Message<?>>();

	// Stateless unless a state generator is provided
	private volatile RetryStateGenerator retryStateGenerator = message -> null;

	/**
	 * Set the retry template. Cause traversal should be enabled in the retry policy
	 * because user exceptions may be wrapped in a {@link MessagingException}.
	 * @param retryTemplate the retry template.
	 */
	public void setRetryTemplate(RetryTemplate retryTemplate) {
		Assert.notNull(retryTemplate, "'retryTemplate' cannot be null");
		this.retryTemplate = retryTemplate;
	}

	public void setRecoveryCallback(RecoveryCallback<Object> recoveryCallback) {
		this.recoveryCallback = recoveryCallback;
	}

	public void setRetryStateGenerator(RetryStateGenerator retryStateGenerator) {
		Assert.notNull(retryStateGenerator, "'retryStateGenerator' cannot be null");
		this.retryStateGenerator = retryStateGenerator;
	}

	@Override
	protected void onInit() {
		super.onInit();
		this.retryTemplate.registerListener(this);
	}

	@Override
	protected Object doInvoke(final ExecutionCallback callback, Object target, final Message<?> message) {
		RetryState retryState = null;
		retryState = this.retryStateGenerator.determineRetryState(message);
		messageHolder.set(message);

		try {
			return this.retryTemplate.execute(context -> callback.cloneAndExecute(), this.recoveryCallback, retryState);
		}
		catch (MessagingException e) {
			if (e.getFailedMessage() == null) {
				throw new MessagingException(message, "Failed to invoke handler", e);
			}
			throw e;
		}
		catch (ThrowableHolderException e) { // NOSONAR catch and rethrow
			throw e;
		}
		catch (Exception e) {
			throw new ThrowableHolderException(e);
		}
		finally {
			messageHolder.remove();
		}
	}

	@Override
	public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
		context.setAttribute(ErrorMessageUtils.FAILED_MESSAGE_CONTEXT_KEY, messageHolder.get());
		return true;
	}

	@Override
	public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
			Throwable throwable) {
	}

	@Override
	public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
			Throwable throwable) {
	}

}
