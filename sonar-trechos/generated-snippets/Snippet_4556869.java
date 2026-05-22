class SimpleTagDirectiveModel extends JspTagModelBase implements TemplateDirectiveModel {
    protected SimpleTagDirectiveModel(String tagName, Class tagClass) throws IntrospectionException {
        super(tagName, tagClass);
        if (!SimpleTag.class.isAssignableFrom(tagClass)) {
            throw new IllegalArgumentException(tagClass.getName() + 
                    " does not implement either the " + Tag.class.getName() + 
                    " interface or the " + SimpleTag.class.getName() + 
                    " interface.");
        }
    }

    public void execute(Environment env, Map args, TemplateModel[] outArgs, 
            final TemplateDirectiveBody body) 
    throws TemplateException, IOException {
        try {
            SimpleTag tag = (SimpleTag) getTagInstance();
            final FreeMarkerPageContext pageContext = PageContextFactory.getCurrentPageContext();
            pageContext.pushWriter(new JspWriterAdapter(env.getOut()));
            try {
                tag.setJspContext(pageContext);
                JspTag parentTag = (JspTag) pageContext.peekTopTag(JspTag.class);
                if (parentTag != null) {
                    tag.setParent(parentTag);
                }
                setupTag(tag, args, pageContext.getObjectWrapper());
                if (body != null) {
                    tag.setJspBody(new JspFragment() {
                        @Override
                        public JspContext getJspContext() {
                            return pageContext;
                        }
                        
                        @Override
                        public void invoke(Writer out) throws JspException, IOException {
                            try {
                                body.render(out == null ? pageContext.getOut() : out);
                            } catch (TemplateException e) {
                                throw new TemplateExceptionWrapperJspException(e);
                            }
                        }
                    });
                    pageContext.pushTopTag(tag);
                    try {
                        tag.doTag();
                    } finally {
                        pageContext.popTopTag();
                    }
                } else {
                    tag.doTag();
                }
            } finally {
                pageContext.popWriter();
            }
        } catch (TemplateException e) {
            throw e;
        } catch (Exception e) {
            throw toTemplateModelExceptionOrRethrow(e);
        }
    }
    
    static final class TemplateExceptionWrapperJspException extends JspException {

        public TemplateExceptionWrapperJspException(TemplateException cause) {
            super("Nested content has thrown template exception", cause);
        }
        
        public TemplateException getCause() {
            return (TemplateException) super.getCause();
        }
        
    }
    
}
