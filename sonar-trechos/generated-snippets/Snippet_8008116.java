public class JdtBasedConstructorScope extends AbstractConstructorScope {

	private static final Logger logger = Logger.getLogger(JdtBasedConstructorScope.class);
	
	public JdtBasedConstructorScope(JdtBasedSimpleTypeScope typeScope) {
		super(typeScope);
	}
	
	@Override
	protected Iterable<IEObjectDescription> internalGetAllElements() {
		IJavaProject javaProject = getTypeProvider().getJavaProject();
		if (javaProject == null)
			return Collections.emptyList();
		final List<IEObjectDescription> allScopedElements = Lists.newArrayListWithExpectedSize(25000);
		try {
			IJavaSearchScope searchScope = SearchEngine.createJavaSearchScope(new IJavaElement[] { javaProject });
			SearchRequestor searchRequestor = new SearchRequestor() {
				@Override
				public void acceptSearchMatch(SearchMatch match) throws CoreException {
					Object element = match.getElement();
					if (element instanceof IMethod) {
						IMethod constructor = (IMethod) element;
						allScopedElements.add(createScopedElement(constructor));	
					} else if (element instanceof IType) {
						allScopedElements.add(createScopedElement((IType)element));
					}
				}
			};
			collectContents(searchScope, searchRequestor);
		}
		catch (CoreException e) {
			logger.error("CoreException when searching for constructors.", e);
		}
		return allScopedElements;
	}
	
	protected IJdtTypeProvider getTypeProvider() {
		return getTypeScope().getTypeProvider();
	}
	
	public IEObjectDescription createScopedElement(IMethod constructor) {
		return new LazyConstructorDescription(constructor, getTypeProvider().getTypeUriHelper(), getQualifiedNameConverter());
	}
	
	/**
	 * @since 2.3
	 */
	public IEObjectDescription createScopedElement(IType type) {
		return new LazyDefaultConstructorDescription(type, getTypeProvider().getTypeUriHelper(), getQualifiedNameConverter());
	}
	
	public void collectContents(IJavaSearchScope searchScope, SearchRequestor searchRequestor) throws CoreException {
		SearchPattern pattern = new ConstructorDeclarationPattern(null, null, SearchPattern.R_PREFIX_MATCH);
		new SearchEngine().search(pattern, SearchUtils.getDefaultSearchParticipants(), searchScope, searchRequestor, new NullProgressMonitor());
	}

	@Override
	public JdtBasedSimpleTypeScope getTypeScope() {
		return (JdtBasedSimpleTypeScope) super.getTypeScope();
	}
	
	public static class LazyConstructorDescription extends AbstractEObjectDescription {

		private final IMethod constructor;
		private final TypeURIHelper uriHelper;
		private final IQualifiedNameConverter converter;
		private QualifiedName qualifiedName = null;
		private JvmConstructor proxy = null;
		private URI uri = null;

		protected LazyConstructorDescription(IMethod constructor, TypeURIHelper uriHelper, IQualifiedNameConverter converter) {
			this.constructor = constructor;
			this.uriHelper = uriHelper;
			this.converter = converter;
		}
		
		@Override
		public QualifiedName getName() {
			return getQualifiedName();
		}

		@Override
		public QualifiedName getQualifiedName() {
			if (qualifiedName == null)
				qualifiedName = converter.toQualifiedName(constructor.getDeclaringType().getFullyQualifiedName('.'));
			return qualifiedName;
		}

		@Override
		public JvmConstructor getEObjectOrProxy() {
			if (proxy == null) {
				proxy = createProxy();
			}
			return proxy;
		}

		@Override
		public URI getEObjectURI() {
			if (uri == null)
				uri = computeURI();
			return uri;
		}

		@Override
		public EClass getEClass() {
			return TypesPackage.Literals.JVM_CONSTRUCTOR;
		}

		protected JvmConstructor createProxy() {
			InternalEObject proxy = (InternalEObject) TypesFactory.eINSTANCE.createJvmConstructor();
			proxy.eSetProxyURI(getEObjectURI());
			return (JvmConstructor) proxy;
		}
		
		protected URI computeURI() {
			return uriHelper.getFullURI(constructor);
		}
		
	}

	/**
	 * @since 2.3
	 */
	public static class LazyDefaultConstructorDescription extends AbstractEObjectDescription {

		private final IType type;
		private final TypeURIHelper uriHelper;
		private final IQualifiedNameConverter converter;
		private QualifiedName qualifiedName = null;
		private JvmConstructor proxy = null;
		private URI uri = null;

		protected LazyDefaultConstructorDescription(IType type, TypeURIHelper uriHelper, IQualifiedNameConverter converter) {
			this.type = type;
			this.uriHelper = uriHelper;
			this.converter = converter;
		}
		
		@Override
		public QualifiedName getName() {
			return getQualifiedName();
		}

		@Override
		public QualifiedName getQualifiedName() {
			if (qualifiedName == null)
				qualifiedName = converter.toQualifiedName(type.getFullyQualifiedName('.'));
			return qualifiedName;
		}

		@Override
		public JvmConstructor getEObjectOrProxy() {
			if (proxy == null) {
				proxy = createProxy();
			}
			return proxy;
		}

		@Override
		public URI getEObjectURI() {
			if (uri == null)
				uri = computeURI();
			return uri;
		}

		@Override
		public EClass getEClass() {
			return TypesPackage.Literals.JVM_CONSTRUCTOR;
		}

		protected JvmConstructor createProxy() {
			InternalEObject proxy = (InternalEObject) TypesFactory.eINSTANCE.createJvmConstructor();
			proxy.eSetProxyURI(getEObjectURI());
			return (JvmConstructor) proxy;
		}
		
		protected URI computeURI() {
			String typeURI = uriHelper.getFullURI(type).toString();
			return URI.createURI(typeURI + '.' + type.getElementName() + "()");
		}
		
	}

}
