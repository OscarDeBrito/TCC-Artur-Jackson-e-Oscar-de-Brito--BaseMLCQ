public class Snippet__9516656 {

    @Override
      public boolean equals(Object other) {
        if (other == null)
          return false;
        if (other.getClass().isAssignableFrom(this.getClass())) {
          return this.getProto().equals(this.getClass().cast(other).getProto());
        }
        return false;
      }

}
