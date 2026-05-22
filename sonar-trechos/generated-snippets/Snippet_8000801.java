@Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PROTECTED_GETTER })
@SuppressWarnings("all")
public abstract class AbstractHierarchyViewPart extends ViewPart {
  private IHierarchyBuilder builder;
  
  private URI rootURI;
  
  public void refresh(final IProgressMonitor monitor) {
    this.setRoot(this.createRoot(monitor));
  }
  
  protected IHierarchyRoot createRoot(final IProgressMonitor monitor) {
    if (((this.builder == null) || (this.rootURI == null))) {
      return IHierarchyRoot.EMPTY;
    }
    final Collection<IHierarchyNode> roots = this.builder.buildRoots(this.rootURI, monitor);
    boolean _isEmpty = roots.isEmpty();
    if (_isEmpty) {
      return IHierarchyRoot.EMPTY;
    }
    final DefaultHierarchyRoot root = new DefaultHierarchyRoot();
    List<IHierarchyNode> _roots = root.getRoots();
    Iterables.<IHierarchyNode>addAll(_roots, roots);
    return root;
  }
  
  protected abstract void setRoot(final IHierarchyRoot root);
  
  protected IHierarchyNode getSelectedNode(final ISelection selection) {
    boolean _matched = false;
    if (selection instanceof IStructuredSelection) {
      int _size = ((IStructuredSelection)selection).size();
      boolean _equals = (_size == 1);
      if (_equals) {
        _matched=true;
        final Object selectedElement = ((IStructuredSelection)selection).getFirstElement();
        if ((selectedElement instanceof IHierarchyNode)) {
          return ((IHierarchyNode)selectedElement);
        }
      }
    }
    return null;
  }
  
  protected <T extends IHierarchyBuilder> T getBuilder(final Class<T> clazz) {
    boolean _isInstance = clazz.isInstance(this.builder);
    if (_isInstance) {
      return ((T) this.builder);
    }
    if ((this.builder instanceof DeferredHierarchyBuilder)) {
      final IHierarchyBuilder wrappedBuilder = ((DeferredHierarchyBuilder)this.builder).getHierarchyBuilder();
      boolean _isInstance_1 = clazz.isInstance(wrappedBuilder);
      if (_isInstance_1) {
        return ((T) wrappedBuilder);
      }
    }
    return null;
  }
  
  @Pure
  protected IHierarchyBuilder getBuilder() {
    return this.builder;
  }
  
  public void setBuilder(final IHierarchyBuilder builder) {
    this.builder = builder;
  }
  
  @Pure
  protected URI getRootURI() {
    return this.rootURI;
  }
  
  public void setRootURI(final URI rootURI) {
    this.rootURI = rootURI;
  }
}
