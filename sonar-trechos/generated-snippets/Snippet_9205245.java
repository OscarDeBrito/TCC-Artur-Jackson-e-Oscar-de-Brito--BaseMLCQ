public class Snippet__9205245 {

    public String getLayoutString( )
    	{
    		StringBuffer sb = new StringBuffer( );

    		sb.append( "table " + table.getFullName( ) + " layout: \r\n" ); //$NON-NLS-1$ //$NON-NLS-2$
    		sb.append( getHeader( ).getLayoutString( ) );
    		sb.append( getGroupHeaders( ).getLayoutString( ) );
    		sb.append( getDetail( ).getLayoutString( ) );
    		sb.append( getGroupFooters( ).getLayoutString( ) );
    		sb.append( getFooter( ).getLayoutString( ) );
    		sb.append( "\r\n" ); //$NON-NLS-1$

    		return sb.toString( );
    	}

}
