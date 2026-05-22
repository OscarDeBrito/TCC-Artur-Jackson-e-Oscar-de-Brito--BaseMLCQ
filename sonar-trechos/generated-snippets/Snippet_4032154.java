public class Snippet__4032154 {

    private QuantileBuffer<T> collapse(Iterable<QuantileBuffer<T>> buffers) {
          int newLevel = 0;
          long newWeight = 0;
          for (QuantileBuffer<T> buffer : buffers) {
            // As presented in the paper, there should always be at least two
            // buffers of the same (minimal) level to collapse, but it is possible
            // to violate this condition when combining buffers from independently
            // computed shards.  If they differ we take the max.
            newLevel = Math.max(newLevel, buffer.level + 1);
            newWeight += buffer.weight;
          }
          List<T> newElements = interpolate(buffers, bufferSize, newWeight, offset(newWeight));
          return new QuantileBuffer<>(newLevel, newWeight, newElements);
        }

}
