@SuppressWarnings("serial")
public class FieldTypeRequiredException extends RuntimeException {

	/**
	 * Creates a new {@code FieldTypeRequiredException} indicating that a type is required
	 * for the reason described in the given {@code message}.
	 * @param message the message
	 */
	public FieldTypeRequiredException(String message) {
		super(message);
	}

}
