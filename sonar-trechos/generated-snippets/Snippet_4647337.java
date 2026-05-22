public class ResourceManagerException extends Exception {
	private static final long serialVersionUID = -5503307426519195160L;

	public ResourceManagerException(String message) {
		super(message);
	}

	public ResourceManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceManagerException(Throwable cause) {
		super(cause);
	}
}
