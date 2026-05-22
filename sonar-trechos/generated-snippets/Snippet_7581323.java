public class Snippet__7581323 {

    @Override
    	public HandlerStrategies.Builder localeContextResolver(LocaleContextResolver localeContextResolver) {
    		Assert.notNull(localeContextResolver, "LocaleContextResolver must not be null");
    		this.localeContextResolver = localeContextResolver;
    		return this;
    	}

}
