public class InvalidAnnotationException extends FormattedRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 *
	 * @param message Message.
	 * @param args Arguments.
	 */
	public InvalidAnnotationException(String message, Object...args) {
		super(message, args);
	}

	/**
	 * Throws an {@link InvalidAnnotationException} if the specified method contains any of the specified annotations.
	 *
	 * @param m The method to check.
	 * @param a The annotations to check for.
	 * @throws InvalidAnnotationException
	 */
	@SafeVarargs
	public static void assertNoInvalidAnnotations(MethodInfo m, Class<? extends Annotation>...a) throws InvalidAnnotationException {
		Annotation aa = m.getAnnotation(a);
		if (aa != null)
			throw new InvalidAnnotationException("@{0} annotation cannot be used in a @{1} bean.  Method=''{2}''", aa.getClass().getSimpleName(), m.getDeclaringClass().getSimpleName(), m);
	}
}
