public class JcrParserException extends CommandException {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3761694498056713525L;

    /**
     * @param message the message
     * @param arguments the arguments
     */
    public JcrParserException(String message, Object[] arguments) {
        super(message, arguments);
    }

    /**
     * @param message the message
     * @param cause the cause
     * @param arguments the arguments
     */
    public JcrParserException(String message, Throwable cause,
        Object[] arguments) {
        super(message, cause, arguments);
    }

    /**
     * @param message the message
     */
    public JcrParserException(String message) {
        super(message);
    }

    /**
     * @param message the message
     * @param cause the cause
     */
    public JcrParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
