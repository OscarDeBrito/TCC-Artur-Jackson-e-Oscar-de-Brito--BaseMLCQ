public class VisorNodeConfigurationCollectorJob extends VisorJob<Void, VisorGridConfiguration> {
    /** */
    private static final long serialVersionUID = 0L;

    /**
     * @param arg Formal job argument.
     * @param debug Debug flag.
     */
    public VisorNodeConfigurationCollectorJob(Void arg, boolean debug) {
        super(arg, debug);
    }

    /** {@inheritDoc} */
    @Override protected VisorGridConfiguration run(Void arg) {
        return new VisorGridConfiguration(ignite);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(VisorNodeConfigurationCollectorJob.class, this);
    }
}
