public class Snippet__7792077 {

    Provision resolve()
    		{

    			if (pageId != null)
    			{
    				IRequestablePage stored = getPageSource().getPageInstance(pageId);
    				if (stored != null && (pageClass == null || pageClass.equals(stored.getClass())))
    				{

    					page = stored;

    					if (renderCount != null && page.getRenderCount() != renderCount)
    						throw new StalePageException(page);
    				}

    				failedToFindStoredPage = page == null;
    			}

    			return this;
    		}

}
