public class ClasspathTypeProviderFactory extends AbstractTypeProviderFactory {

	private final ClassLoader classLoader;
	protected final TypeResourceServices services;
	
	@Inject
	public ClasspathTypeProviderFactory(ClassLoader classLoader, TypeResourceServices services) {
		this.classLoader = classLoader;
		this.services = services;
	}
	
	@Override
	public ClasspathTypeProvider createTypeProvider(ResourceSet resourceSet) {
		if (resourceSet == null)
			throw new IllegalArgumentException("resourceSet may not be null.");
		ClasspathTypeProvider result = createClasspathTypeProvider(resourceSet);
		return result;
	}

	protected ClasspathTypeProvider createClasspathTypeProvider(ResourceSet resourceSet) {
		return new ClasspathTypeProvider(getClassLoader(resourceSet), resourceSet, getIndexedJvmTypeAccess(), services);
	}
	
	public ClassLoader getClassLoader(ResourceSet resourceSet) {
		if (resourceSet instanceof XtextResourceSet) {
			XtextResourceSet xtextResourceSet = (XtextResourceSet) resourceSet;
			Object ctx = xtextResourceSet.getClasspathURIContext();
			if (ctx != null) {
		        if (ctx instanceof Class<?>) {
		            return ((Class<?>)ctx).getClassLoader();
		        }
		        if (!(ctx instanceof ClassLoader)) {
		        	return ctx.getClass().getClassLoader();
		        }
		        return (ClassLoader) ctx;
			}
		}
		return classLoader;
	}
	
	boolean isDefaultClassLoader(ClassLoader loader) {
		return classLoader == loader;
	}
	
	@Override
	public ClasspathTypeProvider createTypeProvider() {
		return (ClasspathTypeProvider) super.createTypeProvider();
	}
	
	@Override
	public IJvmTypeProvider findTypeProvider(ResourceSet resourceSet) {
		return super.findTypeProvider(resourceSet);
	}
	
}
