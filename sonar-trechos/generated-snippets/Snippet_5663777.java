public final class CS_RangeMeaning extends CodeListAdapter<RangeMeaning> {
    /**
     * Empty constructor for JAXB only.
     */
    public CS_RangeMeaning() {
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code RangeMeaning.class}
     */
    @Override
    protected Class<RangeMeaning> getCodeListClass() {
        return RangeMeaning.class;
    }

    /**
     * Sets the default code space to {@code "EPSG"}.
     *
     * @return {@code "EPSG"}.
     */
    @Override
    protected String getCodeSpace() {
        return EPSG;
    }
}
