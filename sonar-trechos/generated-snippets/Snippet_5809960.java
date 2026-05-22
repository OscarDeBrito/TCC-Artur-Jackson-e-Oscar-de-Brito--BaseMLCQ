public class Snippet__5809960 {

    @Override
        public <A> GraphTraversalSource withSideEffect(final String key, final Supplier<A> initialValue, final BinaryOperator<A> reducer) {
            return (GraphTraversalSource) TraversalSource.super.withSideEffect(key, initialValue, reducer);
        }

}
