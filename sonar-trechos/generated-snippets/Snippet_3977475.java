public final class PolygeneServiceAnnotationUtil
{
    /**
     * Returns {@code @Service} annotation if exists.
     *
     * @param modifierListOwner modifier list owner to process.
     * @return {@code @Service} annotation if exists, {@code null} otherwise.
     * @since 0.1
     */
    @Nullable
    public static PsiAnnotation getServiceAnnotation( @NotNull PsiModifierListOwner modifierListOwner )
    {
        return findAnnotation( modifierListOwner, QUALIFIED_NAME_SERVICE_ANNOTATION );
    }

    /**
     * Validates whether the variable has {@code @Service} annotation declared correctly.
     *
     * @param variable variable to check.
     * @return Look at {@link ServiceAnnotationDeclarationValidationResult}.
     * @since 0.1
     */
    @NotNull
    public static ServiceAnnotationDeclarationValidationResult isValidServiceAnnotationDeclaration(
        @NotNull PsiVariable variable )
    {
        PsiAnnotation serviceAnnotation = getServiceAnnotation( variable );
        if( serviceAnnotation == null )
        {
            return invalidServiceAnnotationNotDeclared;
        }

        PsiModifierList modifierList = variable.getModifierList();
        if( modifierList != null )
        {
            if( modifierList.hasModifierProperty( STATIC ) )
            {
                return invalidDeclaredOnStaticVariable;
            }
        }

        // Can't be type that is injected by @Structure
        if( isInjecteableByStructureAnnotation( variable ) )
        {
            return invalidTypeIsInjectedViaStructureAnnotation;
        }

        return valid;
    }

    public enum ServiceAnnotationDeclarationValidationResult
    {
        invalidServiceAnnotationNotDeclared,
        invalidDeclaredOnStaticVariable,
        invalidTypeIsInjectedViaStructureAnnotation,
        valid,
    }

    private PolygeneServiceAnnotationUtil()
    {
    }
}
