public abstract class SideEffectStep<S> extends AbstractStep<S, S> {

    public SideEffectStep(final Traversal.Admin traversal) {
        super(traversal);
    }

    protected abstract void sideEffect(final Traverser.Admin<S> traverser);

    @Override
    protected Traverser.Admin<S> processNextStart() {
        final Traverser.Admin<S> traverser = this.starts.next();
        this.sideEffect(traverser);
        return traverser;
    }
}
