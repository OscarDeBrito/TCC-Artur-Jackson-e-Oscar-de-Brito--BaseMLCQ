public class FoldLeft<T> implements Function<Generator<T>, T>, BinaryFunction<Generator<T>, T, T> {

    /**
     * Helper procedure.
     *
     * @param <T> the returned evaluation type.
     */
    private static class FoldLeftHelper<T> implements Procedure<T> {
        /**
         * The wrapped function.
         */
        private final BinaryFunction<? super T, ? super T, ? extends T> function;
        /**
         * The seed object.
         */
        private T seed;
        /**
         * Flag to check the helper started or not.
         */
        private boolean started;

        /**
         * Create a seedless FoldLeftHelper.
         *
         * @param function The wrapped function
         */
        public FoldLeftHelper(BinaryFunction<? super T, ? super T, ? extends T> function) {
            this(null, function);
        }

        /**
         * Create a new FoldLeftHelper.
         *
         * @param seed initial left-side argument
         * @param function The wrapped function
         */
        FoldLeftHelper(T seed, BinaryFunction<? super T, ? super T, ? extends T> function) {
            this.seed = seed;
            started = seed != null ? true : false;
            this.function = function;
        }

        /**
         * {@inheritDoc}
         */
        public void run(T obj) {
            if (!started) {
                seed = obj;
                started = true;
            } else {
                seed = function.evaluate(seed, obj);
            }
        }

        /**
         * Get current result.
         * @return Object
         */
        T getResult() {
            return started ? seed : null;
        }

    }

    /**
     * {@link BinaryFunction} to apply to each (seed, next).
     */
    private final BinaryFunction<? super T, ? super T, ? extends T> function;

    /**
     * Create a new FoldLeft.
     * @param func {@link BinaryFunction} to apply to each (seed, next)
     */
    public FoldLeft(BinaryFunction<? super T, ? super T, ? extends T> func) {
        this.function = func;
    }

    /**
     * {@inheritDoc}
     * @param obj {@link Generator} to transform
     */
    public final T evaluate(Generator<T> obj) {
        FoldLeftHelper<T> helper = new FoldLeftHelper<T>(function);
        obj.run(helper);
        return helper.getResult();
    }

    /**
     * {@inheritDoc}
     * @param left {@link Generator} to transform
     * @param right initial left-side seed object
     */
    public final T evaluate(Generator<T> left, T right) {
        FoldLeftHelper<T> helper = new FoldLeftHelper<T>(right, function);
        left.run(helper);
        return helper.getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FoldLeft<?>)) {
            return false;
        }
        return ((FoldLeft<?>) obj).function.equals(function);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return "FoldLeft".hashCode() << 2 ^ function.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FoldLeft<" + function + ">";
    }

}
