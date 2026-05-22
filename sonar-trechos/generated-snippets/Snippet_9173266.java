public class Snippet__9173266 {

    public static ContentProvider newInstance( LegendData lgData )
    				throws ChartException
    		{
    			LegendItemType itemType = lgData.lg.getItemType( );
    			if ( itemType.getValue( ) == LegendItemType.CATEGORIES )
    			{
    				return new CategoryContentProvider( lgData );
    			}
    			else if ( itemType.getValue( ) == LegendItemType.SERIES )
    			{
    				return new ValueContentProvider( lgData );
    			}
    			else
    			{
    				throw new ChartException( ChartEnginePlugin.ID,
    						ChartException.GENERATION,
    						"exception.illegal.rendering.legend.itemtype", //$NON-NLS-1$
    						new Object[]{
    							itemType
    						},
    						Messages.getResourceBundle( lgData.rtc.getULocale( ) ) );
    			}

    		}

}
