public class SecondMoment extends FirstMoment implements Serializable {

    /** Serializable version identifier */
    private static final long serialVersionUID = 20150412L;

    /** second moment of values that have been added */
    protected double m2;

    /**
     * Create a SecondMoment instance.
     */
    public SecondMoment() {
        super();
        m2 = Double.NaN;
    }

    /**
     * Copy constructor, creates a new {@code SecondMoment} identical
     * to the {@code original}.
     *
     * @param original the {@code SecondMoment} instance to copy
     * @throws NullArgumentException if original is null
     */
    public SecondMoment(SecondMoment original) throws NullArgumentException {
        super(original);
        this.m2 = original.m2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        if (n < 1) {
            m1 = m2 = 0.0;
        }
        super.increment(d);
        m2 += ((double) n - 1) * dev * nDev;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        m2 = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return m2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecondMoment copy() {
        SecondMoment result = new SecondMoment();
        // no try-catch or advertised NAE because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source SecondMoment to copy
     * @param dest SecondMoment to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(SecondMoment source, SecondMoment dest)
        throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        FirstMoment.copy(source, dest);
        dest.m2 = source.m2;
    }

}
