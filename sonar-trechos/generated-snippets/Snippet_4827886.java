public class CollectionClearFacetViaClearMethod extends CollectionClearFacetAbstract implements ImperativeFacet {

    private final Method method;

    public CollectionClearFacetViaClearMethod(final Method method, final FacetHolder holder) {
        super(holder);
        this.method = method;
    }

    /**
     * Returns a singleton list of the {@link Method} provided in the
     * constructor.
     */
    @Override
    public List<Method> getMethods() {
        return Collections.singletonList(method);
    }

    @Override
    public Intent getIntent(final Method method) {
        return Intent.MODIFY_COLLECTION_ADD;
    }

    @Override
    public void clear(final ObjectAdapter owningAdapter) {
        ObjectAdapter.InvokeUtils.invoke(method, owningAdapter);
    }

    @Override
    protected String toStringValues() {
        return "method=" + method;
    }

    @Override public void appendAttributesTo(final Map<String, Object> attributeMap) {
        super.appendAttributesTo(attributeMap);
        ImperativeFacet.Util.appendAttributesTo(this, attributeMap);
    }

}
