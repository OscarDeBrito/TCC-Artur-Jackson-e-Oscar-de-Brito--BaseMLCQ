public class Snippet__6923161 {

    private static Range<Integer> union(Range<Integer> x, Range<Integer> y) {
          return x.isEmpty() ? y : y.isEmpty() ? x : x.span(y).canonical(INTEGERS);
        }

}
