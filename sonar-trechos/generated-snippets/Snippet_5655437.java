abstract class CollectionConverter<T> extends SystemConverter<Collection<?>,T> {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -9214936334129327955L;

    /**
     * For inner classes only.
     */
    @SuppressWarnings("unchecked")
    CollectionConverter(final Class<T> targetClass) {
        super((Class) Collection.class, targetClass);
    }

    /**
     * Returns {@link FunctionProperty#SURJECTIVE} by default.
     */
    @Override
    public java.util.Set<FunctionProperty> properties() {
        return EnumSet.of(FunctionProperty.SURJECTIVE);
    }

    /**
     * Converter from {@link Collection} to {@link java.util.List}.
     */
    public static final class List extends CollectionConverter<java.util.List<?>> {
        private static final long serialVersionUID = -8680976097058177832L;

        @SuppressWarnings("unchecked")
        public List() {                                 // Instantiated by ServiceLoader.
            super((Class) java.util.List.class);
        }

        @Override
        public java.util.List<?> apply(final Collection<?> source) {
            if (source == null) {
                return null;
            }
            if (source instanceof java.util.List<?>) {
                return (java.util.List<?>) source;
            }
            return new ArrayList<>(source);
        }
    }


    /**
     * Converter from {@link Collection} to {@link java.util.Set}.
     */
    public static final class Set extends CollectionConverter<java.util.Set<?>> {
        private static final long serialVersionUID = -1065360595793529078L;

        @SuppressWarnings("unchecked")
        public Set() {                                  // Instantiated by ServiceLoader.
            super((Class) java.util.Set.class);
        }

        @Override
        public java.util.Set<?> apply(final Collection<?> source) {
            if (source == null) {
                return null;
            }
            if (source instanceof java.util.Set<?>) {
                return (java.util.Set<?>) source;
            }
            return new LinkedHashSet<>(source);
        }
    }
}
