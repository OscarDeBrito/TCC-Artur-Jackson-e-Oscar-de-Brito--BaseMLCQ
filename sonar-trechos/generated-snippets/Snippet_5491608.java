public class Response extends Command
{
    protected String _registeredClientName;
    protected String _errorMessage;
    private CommandType _inReplyToCommandType;

    public Response(final String registeredClientName, final CommandType inReplyToCommandType, final String errorMessage)
    {
        super(CommandType.RESPONSE);
        _registeredClientName = registeredClientName;
        _errorMessage = errorMessage;
        _inReplyToCommandType = inReplyToCommandType;
    }

    public Response(String clientName, CommandType inReplyToCommandType)
    {
        this(clientName, inReplyToCommandType, null);
    }

    /**
     * Provided so that subclasses can call super(commandType)
     */
    protected Response(CommandType commandType)
    {
        super(commandType);
    }

    protected Response()
    {
        super(null);
    }

    public String getRegisteredClientName()
    {
        return _registeredClientName;
    }

    public void setRegisteredClientName(String registeredClientName)
    {
        _registeredClientName = registeredClientName;
    }

    @OutputAttribute(attribute=ParticipantAttribute.ERROR_MESSAGE)
    public String getErrorMessage()
    {
        return _errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        _errorMessage = errorMessage;
    }

    public boolean hasError()
    {
        return _errorMessage != null;
    }

    public CommandType getInReplyToCommandType()
    {
        return _inReplyToCommandType;
    }
}
