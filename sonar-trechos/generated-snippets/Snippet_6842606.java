public class Snippet__6842606 {

    @SuppressWarnings("unchecked")
      public <T> T remove(SyntheticAttributeKey<T> k) {
        if (immutable) {
          throw new UnsupportedOperationException();
        }
        return (T) remove((Object) k);
      }

}
