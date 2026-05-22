static class MergingCombineFn<K, AccumT> extends CombineFn<AccumT, List<AccumT>, AccumT> {

    private final CombineFn<?, AccumT, ?> combineFn;
    private final Coder<AccumT> accumCoder;

    MergingCombineFn(CombineFn<?, AccumT, ?> combineFn, Coder<AccumT> accumCoder) {
      this.combineFn = combineFn;
      this.accumCoder = accumCoder;
    }

    @Override
    public List<AccumT> createAccumulator() {
      ArrayList<AccumT> result = new ArrayList<>();
      result.add(this.combineFn.createAccumulator());
      return result;
    }

    @Override
    public List<AccumT> addInput(List<AccumT> accumulator, AccumT input) {
      accumulator.add(input);
      if (accumulator.size() < MAX_ACCUMULATOR_BUFFER_SIZE) {
        return accumulator;
      } else {
        return mergeToSingleton(accumulator);
      }
    }

    @Override
    public List<AccumT> mergeAccumulators(Iterable<List<AccumT>> accumulators) {
      return mergeToSingleton(Iterables.concat(accumulators));
    }

    @Override
    public List<AccumT> compact(List<AccumT> accumulator) {
      return mergeToSingleton(accumulator);
    }

    @Override
    public AccumT extractOutput(List<AccumT> accumulator) {
      if (accumulator.isEmpty()) {
        return combineFn.createAccumulator();
      } else {
        return combineFn.mergeAccumulators(accumulator);
      }
    }

    private List<AccumT> mergeToSingleton(Iterable<AccumT> accumulators) {
      List<AccumT> singleton = new ArrayList<>();
      singleton.add(combineFn.mergeAccumulators(accumulators));
      return singleton;
    }

    @Override
    public Coder<List<AccumT>> getAccumulatorCoder(CoderRegistry registry, Coder<AccumT> inputCoder)
        throws CannotProvideCoderException {
      return ListCoder.of(accumCoder);
    }
  }
