public static class MaybeSupplier<T> extends AbstractPresent<T> {
        private static final long serialVersionUID = -823731500051341455L;
        private final Supplier<T> supplier;
        public MaybeSupplier(Supplier<T> value) {
            this.supplier = value;
        }
        @Override
        public T get() {
            return supplier.get();
        }
        public Supplier<T> getSupplier() {
            return supplier;
        }
    }
