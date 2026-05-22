abstract class ElementPropertyChangedEvent extends ElementPropertyEvent {

        public ElementPropertyChangedEvent(final Element element, final Property oldValue, final Object newValue, final Object... vertexPropertyKeyValues) {
            super(element, oldValue, newValue, vertexPropertyKeyValues);
        }
    }
