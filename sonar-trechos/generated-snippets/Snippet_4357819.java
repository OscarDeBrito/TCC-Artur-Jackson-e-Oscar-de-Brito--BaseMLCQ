public class AssertEventCatcher implements ConnectionEventListener
{
    private final int catcher;
    //The following flags will indicate what kind of event was
    //received by this listener
    private boolean gotConnectionClosed = false;
    private boolean gotConnectionErrorOccured = false;

    public AssertEventCatcher(int which) {
        catcher=which;
    }

    // ConnectionEventListener methods
    public void connectionClosed(ConnectionEvent event)
    {
        gotConnectionClosed = true;
    }

    public void connectionErrorOccurred(ConnectionEvent event)
    {
        gotConnectionErrorOccured = true;
    }

    /**
     * Tell the caller if we received Connection closed event
     * @return true if received Connection closed event
     */
    public boolean didConnectionClosedEventHappen() 
    {
    	return gotConnectionClosed;
    }
    
    /**
     * Tell the caller if we received Connection error event
     * @return true if received Connection error event
     */
    public boolean didConnectionErrorEventHappen() 
    {
    	return gotConnectionErrorOccured;
    }
    
    /**
     * Clear the event received flags for this listener.
     */
    public void resetState() 
    {
    	gotConnectionClosed = false;
    	gotConnectionErrorOccured = false;
    }
}
