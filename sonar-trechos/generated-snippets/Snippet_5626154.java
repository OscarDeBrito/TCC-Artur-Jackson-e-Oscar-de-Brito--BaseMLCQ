public class Snippet__5626154 {

    public boolean isSuspended() throws NotConnectedException
    	{
    		if (!isConnected())
    			throw new NotConnectedException();

    		return m_isHalted;
    	}

}
