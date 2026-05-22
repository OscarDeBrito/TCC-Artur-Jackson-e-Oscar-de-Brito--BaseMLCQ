public class Snippet__9142325 {

    protected void okPressed( )
    	{
    		if ( canSelect )
    		{
    			setResult( selectedColumnName );
    			if ( inputElement instanceof DataItemHandle )
    			{
    				try
    				{
    					( (DataItemHandle) inputElement ).setResultSetColumn( selectedColumnName );
    				}
    				catch ( SemanticException e )
    				{
    					ExceptionHandler.handle( e );
    				}
    			}
    		}
    		if( getSelectColumnHandle( ) != null && ExtendedDataModelUIAdapterHelper.isBoundToExtendedData( inputElement ) )
    		{
    			DataModelAdapterStatus status = DataModelAdapterUtil.validateRelativeTimePeriod( inputElement, getSelectColumnHandle( ));
    			if( status.getStatus( ) == DataModelAdapterStatus.Status.FAIL )
    			{
    				MessageDialog.openError( UIUtil.getDefaultShell( ), null, status.getMessage( ) );
    				return;
    			}
    		}
    		super.okPressed( );
    	}

}
