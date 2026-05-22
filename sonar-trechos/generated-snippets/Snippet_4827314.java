public class NamedFacetForActionXml extends NamedFacetAbstract {

    public static NamedFacet create(final ActionLayoutData actionLayout, final FacetHolder holder) {
        if(actionLayout == null) {
            return null;
        }
        final String named = Strings.emptyToNull(actionLayout.getNamed());
        Boolean escaped = actionLayout.getNamedEscaped();
        return named != null ? new NamedFacetForActionXml(named, (escaped == null || escaped), holder) : null;
    }

    private NamedFacetForActionXml(final String value, final boolean escaped, final FacetHolder holder) {

        super(value, escaped, holder);
    }

}
