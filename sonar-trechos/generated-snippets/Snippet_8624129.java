public abstract class ProcessManagerWrapper<T, R> implements ProcessManager<R> {
    /** Delegate. */
    private final ProcessManager<T> delegate;

    /**
     * Constructs a new instance of process manager wrapper.
     *
     * @param delegate Delegate.
     */
    public ProcessManagerWrapper(ProcessManager<T> delegate) {
        assert delegate != null : "Delegate should not be null";

        this.delegate = delegate;
    }

    /**
     * Transforms accepted process specification into process specification delegate working with.
     *
     * @param spec Accepted process specification.
     * @return Process specification delegate working with.
     */
    protected abstract T transformSpecification(R spec);

    /** {@inheritDoc} */
    @Override public Map<UUID, List<UUID>> start(List<R> specifications) {
        List<T> transformedSpecifications = new ArrayList<>();

        for (R spec : specifications)
            transformedSpecifications.add(transformSpecification(spec));

        return delegate.start(transformedSpecifications);
    }

    /** {@inheritDoc} */
    @Override public Map<UUID, List<LongRunningProcessStatus>> ping(Map<UUID, List<UUID>> procIds) {
        return delegate.ping(procIds);
    }

    /** {@inheritDoc} */
    @Override public Map<UUID, List<LongRunningProcessStatus>> stop(Map<UUID, List<UUID>> procIds, boolean clear) {
        return delegate.stop(procIds, clear);
    }

    /** {@inheritDoc} */
    @Override public Map<UUID, List<LongRunningProcessStatus>> clear(Map<UUID, List<UUID>> procIds) {
        return delegate.clear(procIds);
    }
}
