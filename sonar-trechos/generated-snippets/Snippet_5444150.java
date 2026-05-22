public class Snippet__5444150 {

    public void setLocale(Locale locale)
    	{
    	    if (locale != null)
    	    {
                responseContext.setLocale(locale);
    	        if (canSetLocaleEncoding)
    	        {
    	            String encoding = getPortletWindow().getPortletDefinition().getApplication().getLocaleEncodingMappings().get(locale);
    	            if (encoding != null)
    	            {
    	                setCharacterEncoding(encoding);
    	                // allow repeated setLocale usage for characterEncoding
    	                canSetLocaleEncoding = true;
    	            }
    	        }
    	    }
    	}

}
