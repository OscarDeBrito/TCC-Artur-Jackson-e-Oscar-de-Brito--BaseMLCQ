public class Snippet__9158528 {

    private void createImageMapContainer( int x, int y, int width, int height,
    			IHyperlinkAction link )
    	{
    		ReportContent reportContent = (ReportContent) image.getReportContent( );
    		IContainerContent mapContent = reportContent.createContainerContent( );
    		mapContent.setHyperlinkAction( link );
    		BlockContainerArea area = (BlockContainerArea) AreaFactory
    				.createBlockContainer( mapContent );
    		area.setPosition( x, y );
    		area.setWidth( width );
    		area.setHeight( height );
    		root.addChild( area );
    	}

}
