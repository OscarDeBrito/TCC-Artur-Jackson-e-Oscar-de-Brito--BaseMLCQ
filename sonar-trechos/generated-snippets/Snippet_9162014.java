public class Messages
{
	private static ResourceBundle rb = ResourceBundle.getBundle( 
			"org.eclipse.birt.report.data.oda.sampledb.ui.i18n.Messages" );
	
	public static String getMessage( String key )
	{
		try
		{
			if ( rb != null )
				return rb.getString( key );
			// Fall through to return key
		}
		catch ( MissingResourceException e )
		{
		}
		return  " #" + key + "# ";		
	}
	
	public static String formatMessage( String key, Object[] args) 
	{
		return MessageFormat.format( getMessage(key), args);
	}
}
