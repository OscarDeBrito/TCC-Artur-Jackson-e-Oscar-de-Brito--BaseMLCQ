public class Snippet__9121712 {

    public Snippet__9121712( ITextContent textContent, int offset, int baseLevel, int runLevel,
    			FontInfo fontInfo )
    	{
    		super(textContent);
    		this.textContent = textContent;
    		this.fi = fontInfo;
    		height = (int)( fi.getWordHeight( ) * PDFConstants.LAYOUT_TO_PDF_RATIO );
    		baseLine = this.fi.getBaseline( );
    		this.offset = offset;
    		this.runLevel = runLevel;
    		this.lineBreak = false;
    		removePadding( );
    		removeBorder( );
    		removeMargin( );
    	}

}
