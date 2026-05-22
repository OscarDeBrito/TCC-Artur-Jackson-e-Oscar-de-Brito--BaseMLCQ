public class Snippet__4865706 {

    public static final class DoubleStandardDeviation implements DoubleFunction {
        @Override
        public double apply(double... values) {
          if(values.length <= 1)
            return NULL;
          double mean = MEAN.apply(values);
          double var = 0.0;
          for(double v : values)
            var += (v - mean) * (v - mean);
          return Math.sqrt(var / (values.length - 1));
        }
      }

}
