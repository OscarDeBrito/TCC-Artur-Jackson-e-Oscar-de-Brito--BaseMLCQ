public class Snippet__4128743 {

    @Override public boolean equals(Object obj) {
          return obj == this
              || obj instanceof Measure
              && this.agg.equals(((Measure) obj).agg)
              && this.args.equals(((Measure) obj).args)
              && this.distinct == ((Measure) obj).distinct;
        }

}
