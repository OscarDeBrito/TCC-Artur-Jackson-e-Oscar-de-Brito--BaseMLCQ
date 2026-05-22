public class Snippet__7782626 {

    protected boolean makeContentVisible()
    	{
    		// if user is refreshing whole page, the window will not be shown
    		if (getWebRequest().isAjax() == false)
    		{
    			return false;
    		}
    		else
    		{
    			return shown;
    		}
    	}

}
