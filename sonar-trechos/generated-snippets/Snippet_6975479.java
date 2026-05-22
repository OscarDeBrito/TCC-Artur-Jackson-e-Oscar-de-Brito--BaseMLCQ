public class Snippet__6975479 {

    @Override
        public SortedMap<R, Map<C, V>> rowMap() {
          Function<Map<C, V>, Map<C, V>> wrapper = unmodifiableWrapper();
          return Collections.unmodifiableSortedMap(Maps.transformValues(delegate().rowMap(), wrapper));
        }

}
