public class Snippet__7982015 {

    protected Bug288734TestLanguageRuntimeModule createRuntimeModule() {
    		// make it work also with Maven/Tycho and OSGI
    		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=493672
    		return new Bug288734TestLanguageRuntimeModule() {
    			@Override
    			public ClassLoader bindClassLoaderToInstance() {
    				return Bug288734TestLanguageInjectorProvider.class
    						.getClassLoader();
    			}
    		};
    	}

}
