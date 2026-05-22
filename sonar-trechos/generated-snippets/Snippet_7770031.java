public class Snippet__7770031 {

    @Benchmark
      @BenchmarkMode(Mode.AverageTime)
      @OutputTimeUnit(TimeUnit.MICROSECONDS)
      public void uncompressed(Blackhole blackhole)
      {
        final ImmutableConciseSet set = ImmutableConciseSet.complement(null, emptyRows);
        blackhole.consume(set);
        assert (emptyRows == set.size());
      }

}
