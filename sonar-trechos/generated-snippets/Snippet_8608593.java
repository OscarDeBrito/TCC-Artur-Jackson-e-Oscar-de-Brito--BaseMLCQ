public class VisorLifecycleConfiguration extends VisorDataTransferObject{
    /** */
    private static final long serialVersionUID = 0L;

    /** Lifecycle beans. */
    private String beans;

    /**
     * Default constructor.
     */
    public VisorLifecycleConfiguration() {
        // No-op.
    }

    /**
     * Create data transfer object for node lifecycle configuration properties.
     *
     * @param c Grid configuration.
     */
    public VisorLifecycleConfiguration(IgniteConfiguration c) {
        beans = compactArray(c.getLifecycleBeans());
    }

    /**
     * @return Lifecycle beans.
     */
    @Nullable public String getBeans() {
        return beans;
    }

    /** {@inheritDoc} */
    @Override protected void writeExternalData(ObjectOutput out) throws IOException {
        U.writeString(out, beans);
    }

    /** {@inheritDoc} */
    @Override protected void readExternalData(byte protoVer, ObjectInput in) throws IOException, ClassNotFoundException {
        beans = U.readString(in);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(VisorLifecycleConfiguration.class, this);
    }
}
