public final class FactoryCreateHandler
    implements AnnotationHandler<FactoryCreate, Class<?>>
{

    /**
     * {@inheritDoc}
     */
    public void handle( FactoryCreate annotation, Class<?> element, RulesBinder rulesBinder )
    {
        FactoryCreateBuilder builder = rulesBinder.forPattern( annotation.pattern() )
            .withNamespaceURI( annotation.namespaceURI() )
            .factoryCreate()
            .overriddenByAttribute( annotation.attributeName().length() > 0 ? annotation.attributeName() : null )
            .ignoreCreateExceptions( annotation.ignoreCreateExceptions() );

        if ( FactoryCreate.DefaultObjectCreationFactory.class != annotation.factoryClass() )
        {
            builder.ofType( annotation.factoryClass() );
        }
    }

}
