public class Snippet__9119349 {

    public Point computeSize( int wHint, int hHint, boolean changed )
    	{
    		checkWidget( );
    		Point e = getTotalSize( image, text );
    		if ( wHint == SWT.DEFAULT )
    		{
    			e.x += leftMargin + rightMargin;
    		}
    		else
    		{
    			e.x = wHint;
    		}
    		if ( hHint == SWT.DEFAULT )
    		{
    			e.y += topMargin + bottomMargin;
    		}
    		else
    		{
    			e.y = hHint;
    		}
    		return e;
    	}

}
