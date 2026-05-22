public final class DQ_PositionalAccuracy extends PropertyType<DQ_PositionalAccuracy, PositionalAccuracy> {
    /**
     * Empty constructor for JAXB only.
     */
    public DQ_PositionalAccuracy() {
    }

    /**
     * Returns the GeoAPI interface which is bound by this adapter.
     * This method is indirectly invoked by the private constructor
     * below, so it shall not depend on the state of this object.
     *
     * @return {@code PositionalAccuracy.class}
     */
    @Override
    protected Class<PositionalAccuracy> getBoundType() {
        return PositionalAccuracy.class;
    }

    /**
     * Constructor for the {@link #wrap} method only.
     */
    private DQ_PositionalAccuracy(final PositionalAccuracy metadata) {
        super(metadata);
    }

    /**
     * Invoked by {@link PropertyType} at marshalling time for wrapping the given metadata value
     * in a {@code <mdq:DQ_PositionalAccuracy>} XML element.
     *
     * @param  metadata  the metadata element to marshall.
     * @return a {@code PropertyType} wrapping the given the metadata element.
     */
    @Override
    protected DQ_PositionalAccuracy wrap(final PositionalAccuracy metadata) {
        return new DQ_PositionalAccuracy(metadata);
    }

    /**
     * Invoked by JAXB at marshalling time for getting the actual metadata to write
     * inside the {@code <mdq:DQ_PositionalAccuracy>} XML element.
     * This is the value or a copy of the value given in argument to the {@code wrap} method.
     *
     * @return the metadata to be marshalled.
     */
    @XmlElementRef
    public AbstractPositionalAccuracy getElement() {
        return AbstractPositionalAccuracy.castOrCopy(metadata);
    }

    /**
     * Invoked by JAXB at unmarshalling time for storing the result temporarily.
     *
     * @param  metadata  the unmarshalled metadata.
     */
    public void setElement(final AbstractPositionalAccuracy metadata) {
        this.metadata = metadata;
    }
}
