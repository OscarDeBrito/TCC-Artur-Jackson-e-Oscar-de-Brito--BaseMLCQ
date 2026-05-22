public class Snippet__7560859 {

    @Nullable
    	private ResourceUrlProvider findResourceUrlProvider(HttpServletRequest request) {
    		if (this.resourceUrlProvider != null) {
    			return this.resourceUrlProvider;
    		}
    		return (ResourceUrlProvider) request.getAttribute(
    				ResourceUrlProviderExposingInterceptor.RESOURCE_URL_PROVIDER_ATTR);
    	}

}
