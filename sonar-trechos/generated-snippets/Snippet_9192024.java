public class Snippet__9192024 {

    public void removeModifyListener( ModifyListener listener )
    	{
    		checkWidget( );
    		if ( listener == null )
    			SWT.error( SWT.ERROR_NULL_ARGUMENT );
    		removeListener( SWT.Modify, listener );
    	}

}
