public class Snippet__6977042 {

    @Override
        public boolean equals(@NullableDecl Object obj) {
          if (this == obj) {
            return true;
          }
          if (obj instanceof EquivalentToPredicate) {
            EquivalentToPredicate<?> that = (EquivalentToPredicate<?>) obj;
            return equivalence.equals(that.equivalence) && Objects.equal(target, that.target);
          }
          return false;
        }

}
