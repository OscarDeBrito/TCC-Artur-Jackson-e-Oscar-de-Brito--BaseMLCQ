@SuppressWarnings("all")
public class UniqueClassNameValidator extends AbstractDeclarativeValidator {
  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;
  
  @Inject
  private IQualifiedNameProvider qualifiedNameProvider;
  
  @Inject
  private IJvmModelAssociations associations;
  
  @Override
  public void register(final EValidatorRegistrar registrar) {
  }
  
  @Inject
  protected void register(final EValidatorRegistrar registrar, final IGrammarAccess grammarAccess) {
    final AbstractRule entryRule = IterableExtensions.<AbstractRule>head(grammarAccess.getGrammar().getRules());
    if ((entryRule instanceof ParserRule)) {
      registrar.register(((ParserRule)entryRule).getType().getClassifier().getEPackage(), this);
    }
  }
  
  @Check
  public void checkUniqueName(final EObject root) {
    EObject _eContainer = root.eContainer();
    boolean _tripleEquals = (_eContainer == null);
    if (_tripleEquals) {
      final Resource resource = root.eResource();
      EObject _head = IterableExtensions.<EObject>head(resource.getContents());
      boolean _equals = Objects.equal(_head, root);
      if (_equals) {
        final Consumer<JvmDeclaredType> _function = (JvmDeclaredType it) -> {
          this.doCheckUniqueName(it);
        };
        Iterables.<JvmDeclaredType>filter(resource.getContents(), JvmDeclaredType.class).forEach(_function);
      }
    }
  }
  
  protected void doCheckUniqueName(final JvmDeclaredType type) {
    EObject _eContainer = type.eContainer();
    boolean _tripleEquals = (_eContainer == null);
    if (_tripleEquals) {
      final QualifiedName name = this.qualifiedNameProvider.getFullyQualifiedName(type);
      if ((name != null)) {
        this.doCheckUniqueName(name, type);
      }
    }
  }
  
  protected boolean doCheckUniqueName(final QualifiedName name, final JvmDeclaredType type) {
    final IResourceDescriptions index = this.resourceDescriptionsProvider.getResourceDescriptions(type.eResource());
    final Iterable<IEObjectDescription> others = index.getExportedObjects(TypesPackage.Literals.JVM_DECLARED_TYPE, name, false);
    return this.checkUniqueInIndex(type, others);
  }
  
  protected boolean checkUniqueInIndex(final JvmDeclaredType type, final Iterable<IEObjectDescription> descriptions) {
    final Function1<IEObjectDescription, URI> _function = (IEObjectDescription it) -> {
      return it.getEObjectURI().trimFragment();
    };
    final Set<URI> resourceURIs = IterableExtensions.<URI>toSet(IterableExtensions.<IEObjectDescription, URI>map(descriptions, _function));
    int _size = resourceURIs.size();
    boolean _greaterThan = (_size > 1);
    if (_greaterThan) {
      final Function1<URI, Boolean> _function_1 = (URI it) -> {
        URI _uRI = type.eResource().getURI();
        return Boolean.valueOf((!Objects.equal(it, _uRI)));
      };
      this.addIssue(type, IterableExtensions.<URI>head(IterableExtensions.<URI>filter(resourceURIs, _function_1)).lastSegment());
      return false;
    } else {
      int _size_1 = IterableExtensions.size(descriptions);
      boolean _greaterThan_1 = (_size_1 > 1);
      if (_greaterThan_1) {
        this.addIssue(type);
        return false;
      }
    }
    return true;
  }
  
  /**
   * Marks a type as already defined.
   * @since 2.15
   */
  protected void addIssue(final JvmDeclaredType type) {
    this.addIssue(type, null);
  }
  
  /**
   * Marks a type as already defined.
   * 
   * @param type - the type to mark already defined.
   * @param fileName - a file where the type is already defined.
   * 					 If fileName is null this information will not be part of the message.
   */
  protected void addIssue(final JvmDeclaredType type, final String fileName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("The type ");
    String _simpleName = type.getSimpleName();
    _builder.append(_simpleName);
    _builder.append(" is already defined");
    {
      if ((fileName != null)) {
        _builder.append(" in ");
        _builder.append(fileName);
      }
    }
    _builder.append(".");
    final String message = _builder.toString();
    final EObject sourceElement = this.associations.getPrimarySourceElement(type);
    if ((sourceElement == null)) {
      this.addIssue(message, type, IssueCodes.DUPLICATE_TYPE);
    } else {
      final EStructuralFeature feature = sourceElement.eClass().getEStructuralFeature("name");
      this.addIssue(message, sourceElement, feature, IssueCodes.DUPLICATE_TYPE);
    }
  }
}
