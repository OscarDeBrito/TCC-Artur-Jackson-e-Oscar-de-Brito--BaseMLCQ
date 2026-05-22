public class Snippet__8763437 {

    public int compareTo(Range o) {
          // Ensure sgn(x.compareTo(y) == -sgn(y.compareTo(x))
          return this.startIndex < o.startIndex ? -1 :
              (this.startIndex > o.startIndex ? 1 :
              (this.length < o.length ? -1 :
              (this.length > o.length ? 1 : 0)));
        }

}
