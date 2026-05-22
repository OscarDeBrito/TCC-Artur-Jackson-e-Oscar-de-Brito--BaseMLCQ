public class Snippet__6120286 {

    @Override
            public <V> UnsortedBagBatch<V> flatCollect(Function<? super T, ? extends Iterable<V>> function)
            {
                return new FlatCollectUnsortedBagBatch<>(this, function);
            }

}
