public abstract class BasicAspectContextImpl implements BasicAspectContext {

    /** The portal service. */
    protected final PortalService portalService;

    /** The iterator used to iterate over the aspects. */
    protected final Iterator aspectsIterator;
    /** The iterator used to iterate over the configuration of the aspects. */
    protected final Iterator propertiesIterator;

    /** The current configuration. */
    protected Properties aspectProperties;

    public BasicAspectContextImpl(PortalService service,
                                  AspectChainImpl   chain) {
        this.portalService = service;
        if ( chain != null ) {
            this.aspectsIterator = chain.getAspectsIterator();
            this.propertiesIterator = chain.getPropertiesIterator();
        } else {
            this.aspectsIterator = EmptyIterator.INSTANCE;
            this.propertiesIterator = EmptyIterator.INSTANCE;
        }
    }

    /**
     * @see org.apache.cocoon.portal.services.aspects.BasicAspectContext#getAspectProperties()
     */
    public Properties getAspectProperties() {
        return this.aspectProperties;
    }

    /**
     * @see org.apache.cocoon.portal.services.aspects.BasicAspectContext#getPortalService()
     */
    public PortalService getPortalService() {
        return this.portalService;
    }

    protected Object getNext() {
        if (this.aspectsIterator.hasNext()) {
            this.aspectProperties = (Properties)this.propertiesIterator.next();
            return this.aspectsIterator.next();
        }
        return null;
    }
}
