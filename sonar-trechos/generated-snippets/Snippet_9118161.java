public class Snippet__9118161 {

    private DataSetHandle getDataSet( ScalarParameterHandle handle )
    	{
    		if ( !isSingle( ) )
    		{
    			if ( handle != null && handle.getDataSet( ) != null )
    			{
    				return handle.getDataSet( );
    			}
    			return null;
    		}
    		return inputParameterGroup.getDataSet( );
    	}

}
