public class HiddenFacetForActionLayoutAnnotation extends HiddenFacetAbstract {

    public static HiddenFacet create(final ActionLayout actionLayout, final FacetHolder holder) {
        if (actionLayout == null) {
            return null;
        }
        final Where where = actionLayout.hidden();
        return where != null && where != Where.NOT_SPECIFIED  ? new HiddenFacetForActionLayoutAnnotation(where, holder) : null;
    }

    private HiddenFacetForActionLayoutAnnotation(final Where where, final FacetHolder holder) {
        super(When.ALWAYS, where, holder);
    }

    @Override
    public String hiddenReason(final ObjectAdapter targetAdapter, final Where whereContext) {
        if(!where().includes(whereContext)) {
            return null;
        }
        return "Hidden on " + where().getFriendlyName();
    }

}
