public class JavaAwtImageValueFacetUsingSemanticsProviderFactory extends ValueFacetUsingSemanticsProviderFactory<Image> {

    public JavaAwtImageValueFacetUsingSemanticsProviderFactory() {
        super();
    }

    @Override
    public void process(final ProcessClassContext processClassContext) {
        final Class<?> type = processClassContext.getCls();
        final FacetHolder holder = processClassContext.getFacetHolder();

        if (type != java.awt.Image.class) {
            return;
        }
        addFacets(new JavaAwtImageValueSemanticsProvider(holder, getContext()));
    }

}
