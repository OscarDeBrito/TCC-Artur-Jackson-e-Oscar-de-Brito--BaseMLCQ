public class Snippet__6985429 {

    ClosureType toNonNullable() {
          return isNullable() ? new ClosureBangDecoratedType(this) : this;
        }

}
