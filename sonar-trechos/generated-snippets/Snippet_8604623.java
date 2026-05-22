public class GridClientJdkMarshaller implements GridClientMarshaller {
    /** ID. */
    public static final byte ID = 2;

    /** Class name filter. */
    private final IgnitePredicate<String> clsFilter;

    /**
     * Default constructor.
     */
    public GridClientJdkMarshaller() {
        this(null);
    }

    /**
     * @param clsFilter Class filter.
     */
    public GridClientJdkMarshaller(IgnitePredicate<String> clsFilter) {
        this.clsFilter = clsFilter;
    }

    /** {@inheritDoc} */
    @Override public ByteBuffer marshal(Object obj, int off) throws IOException {
        GridByteArrayOutputStream bOut = new GridByteArrayOutputStream();

        ObjectOutput out = new ObjectOutputStream(bOut);

        out.writeObject(obj);

        out.flush();

        ByteBuffer buf = ByteBuffer.allocate(off + bOut.size());

        buf.position(off);

        buf.put(bOut.internalArray(), 0, bOut.size());

        buf.flip();

        return buf;
    }

    /** {@inheritDoc} */
    @Override public <T> T unmarshal(byte[] bytes) throws IOException {
        ByteArrayInputStream tmp = new ByteArrayInputStream(bytes);

        ObjectInput in = new ClientJdkInputStream(tmp, clsFilter);

        try {
            return (T)in.readObject();
        }
        catch (ClassNotFoundException e) {
            throw new IOException("Failed to unmarshal target object: " + e.getMessage(), e);
        }
    }

    /**
     * Wrapper with class resolving control.
     */
    private static class ClientJdkInputStream extends ObjectInputStream {
        /** Class name filter. */
        private final IgnitePredicate<String> clsFilter;


        /**
         * @param in Input stream.
         * @param clsFilter Class filter.
         */
        public ClientJdkInputStream(InputStream in, IgnitePredicate<String> clsFilter) throws IOException {
            super(in);

            this.clsFilter = clsFilter;
        }

        /** {@inheritDoc} */
        @Override protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String clsName = desc.getName();

            if (clsFilter != null && !clsFilter.apply(clsName))
                throw new RuntimeException("Deserialization of class " + clsName + " is disallowed.");

            return super.resolveClass(desc);
        }
    }
}
