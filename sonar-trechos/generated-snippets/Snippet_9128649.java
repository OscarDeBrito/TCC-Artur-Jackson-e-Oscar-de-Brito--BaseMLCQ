public class Snippet__9128649 {

    private void fireEvent( Event event )
    	{
    		for ( int i = 0; i < vListeners.size( ); i++ )
    		{
    			vListeners.get( i ).handleEvent( event );
    		}
    	}

}
