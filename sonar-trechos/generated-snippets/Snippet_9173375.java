public class Snippet__9173375 {

    protected void openHyperlink( HyperlinkInfo info )
    	{
    		if ( info == null )
    		{
    			return;
    		}
    		writer.openTag( "w:hlink" );
    		if ( HyperlinkInfo.BOOKMARK == info.getType( ) )
    		{
    			writer.attribute( "w:bookmark", info.getUrl( ) );
    		}
    		else if ( HyperlinkInfo.HYPERLINK == info.getType( ) )
    		{
    			writer.attribute( "w:dest", info.getUrl( ) );
    			if ( info.getBookmark( ) != null )
    			{
    				writer.attribute( "w:bookmark", info.getBookmark( ) );
    			}
    		}
    		if ( info.getTooltip( ) != null )
    		{
    			writer.attribute( "w:screenTip", info.getTooltip( ) );
    		}
    	}

}
