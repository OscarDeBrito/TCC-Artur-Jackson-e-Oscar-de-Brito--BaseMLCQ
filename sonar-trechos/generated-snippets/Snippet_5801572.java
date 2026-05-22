public class DelegatingAnnotationAccess implements AnnotationAccess
{
    private final AnnotationAccess primary;

    private final AnnotationAccess inherited;

    public DelegatingAnnotationAccess(AnnotationAccess primary, AnnotationAccess inherited)
    {
        this.primary = primary;
        this.inherited = inherited;
    }

    private boolean isInherited(Class<? extends Annotation> annotationType)
    {
        return annotationType.getAnnotation(Inherited.class) != null;
    }

    @Override
    public <T extends Annotation> boolean hasAnnotation(Class<T> annotationType)
    {
        if (primary.hasAnnotation(annotationType))
            return true;

        return isInherited(annotationType) && inherited.hasAnnotation(annotationType);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationType)
    {
        T fromPrimary = primary.getAnnotation(annotationType);

        if (fromPrimary != null)
            return fromPrimary;

        return isInherited(annotationType) ? inherited.getAnnotation(annotationType) : null;
    }
}
