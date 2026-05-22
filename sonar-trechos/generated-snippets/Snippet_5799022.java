@SupportsInformalParameters
public abstract class AbstractConditional
{
    @Inject
    private ComponentResources resources;

    /**
     * Performs the test via the parameters; return true to render the body of the component, false to render the else
     * block (or nothing).
     *
     * @return true to render body
     */
    protected abstract boolean test();

    /**
     * An alternate {@link org.apache.tapestry5.Block} to render if {@link #test()} is false. The default, null, means
     * render nothing in that situation.
     */
    @Parameter(name = "else", defaultPrefix = BindingConstants.LITERAL)
    private Block elseBlock;

    private boolean renderTag;

    /**
     * Returns null if the {@link #test()} is true, which allows normal rendering (of the body). If the test parameter
     * is false, returns the else parameter (this may also be null).
     */
    Object beginRender(MarkupWriter writer)
    {
        boolean enabled = test();

        Block toRender = enabled ? resources.getBody() : elseBlock;

        String elementName = resources.getElementName();

        if (enabled && elementName != null)
        {
            renderTag = true;
            writer.element(elementName);
            resources.renderInformalParameters(writer);
        }

        return toRender;
    }

    /**
     * If {@link #test()} is true, then the body is rendered, otherwise not. The component does not have a template or
     * do any other rendering besides its body (and possibly an element around its body).
     */
    boolean beforeRenderBody()
    {
        return false;
    }

    void afterRender(MarkupWriter writer)
    {
        if (renderTag)
        {
            writer.end();
            renderTag = false;
        }
    }


}
