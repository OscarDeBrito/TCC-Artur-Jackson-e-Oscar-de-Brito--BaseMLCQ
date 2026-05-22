public class NamedFacetForActionLayoutAnnotation extends NamedFacetAbstract {

    public static NamedFacet create(final ActionLayout actionLayout, final FacetHolder holder) {
        if(actionLayout == null) {
            return null;
        }
        final String named = Strings.emptyToNull(actionLayout.named());
        return named != null ? new NamedFacetForActionLayoutAnnotation(named, holder) : null;
    }

    private NamedFacetForActionLayoutAnnotation(final String value, final FacetHolder holder) {

        super(value, /*escaped*/ true, holder);
    }

}
