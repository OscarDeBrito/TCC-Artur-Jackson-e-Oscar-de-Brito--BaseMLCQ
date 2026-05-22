@SuppressWarnings("all")
public class XtendAnnotationTypeDeclarationImpl extends XtendTypeDeclarationImpl<XtendAnnotationType> implements AnnotationTypeDeclaration {
  @Override
  public AnnotationTypeElementDeclaration findDeclaredAnnotationTypeElement(final String name) {
    final Function1<AnnotationTypeElementDeclaration, Boolean> _function = (AnnotationTypeElementDeclaration it) -> {
      String _simpleName = it.getSimpleName();
      return Boolean.valueOf(Objects.equal(_simpleName, name));
    };
    return IterableExtensions.findFirst(this.getDeclaredAnnotationTypeElements(), _function);
  }
  
  @Override
  public Iterable<? extends AnnotationTypeElementDeclaration> getDeclaredAnnotationTypeElements() {
    final Function1<XtendMember, MemberDeclaration> _function = (XtendMember it) -> {
      return this.getCompilationUnit().toXtendMemberDeclaration(it);
    };
    return Iterables.<AnnotationTypeElementDeclaration>filter(ListExtensions.<XtendMember, MemberDeclaration>map(this.getDelegate().getMembers(), _function), AnnotationTypeElementDeclaration.class);
  }
}
