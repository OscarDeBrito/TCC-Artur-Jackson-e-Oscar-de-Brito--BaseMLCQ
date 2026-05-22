public interface LabelBindingListener {
    /**
     * Label binding listeners.
     */
    public static class Listeners extends ListenerList<LabelBindingListener>
        implements LabelBindingListener {
        @Override
        public void textKeyChanged(Label label, String previousTextKey) {
            forEach(listener -> listener.textKeyChanged(label, previousTextKey));
        }

        @Override
        public void textBindTypeChanged(Label label, BindType previousTextBindType) {
            forEach(listener -> listener.textBindTypeChanged(label, previousTextBindType));
        }

        @Override
        public void textBindMappingChanged(Label label,
            Label.TextBindMapping previousTextBindMapping) {
            forEach(listener -> listener.textBindMappingChanged(label, previousTextBindMapping));
        }
    }

    /**
     * Label binding listener adapter.
     * @deprecated Since 2.1 and Java 8 the interface itself has default implementations.
     */
    @Deprecated
    public static class Adapter implements LabelBindingListener {
        @Override
        public void textKeyChanged(Label label, String previousTextKey) {
            // empty block
        }

        @Override
        public void textBindTypeChanged(Label label, BindType previousTextBindType) {
            // empty block
        }

        @Override
        public void textBindMappingChanged(Label label,
            Label.TextBindMapping previousTextBindMapping) {
            // empty block
        }
    }

    /**
     * Called when a label's text key has changed.
     *
     * @param label           The label whose binding has changed.
     * @param previousTextKey The previous binding key for the label text.
     */
    default void textKeyChanged(Label label, String previousTextKey) {
    }

    /**
     * Called when a label's text bind type has changed.
     *
     * @param label                The label whose binding has changed.
     * @param previousTextBindType The previous bind type for the label text.
     */
    default void textBindTypeChanged(Label label, BindType previousTextBindType) {
    }

    /**
     * Called when a label's text bind mapping has changed.
     *
     * @param label                   The label whose binding has changed.
     * @param previousTextBindMapping The previous bind mapping for the label text.
     */
    default void textBindMappingChanged(Label label, Label.TextBindMapping previousTextBindMapping) {
    }
}
