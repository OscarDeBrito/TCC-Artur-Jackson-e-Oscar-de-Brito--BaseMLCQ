@VisibleForTesting
  static class FlattenWithoutDuplicateInputs<T>
      extends PTransform<PCollectionList<T>, PCollection<T>> {
    @Override
    public PCollection<T> expand(PCollectionList<T> input) {
      Map<PCollection<T>, Integer> instances = new HashMap<>();
      for (PCollection<T> pCollection : input.getAll()) {
        int existing = instances.get(pCollection) == null ? 0 : instances.get(pCollection);
        instances.put(pCollection, existing + 1);
      }
      PCollectionList<T> output = PCollectionList.empty(input.getPipeline());
      for (Map.Entry<PCollection<T>, Integer> instanceEntry : instances.entrySet()) {
        if (instanceEntry.getValue().equals(1)) {
          output = output.and(instanceEntry.getKey());
        } else {
          String duplicationName = String.format("Multiply %s", instanceEntry.getKey().getName());
          PCollection<T> duplicated =
              instanceEntry
                  .getKey()
                  .apply(duplicationName, ParDo.of(new DuplicateFn<>(instanceEntry.getValue())));
          output = output.and(duplicated);
        }
      }
      return output.apply(Flatten.pCollections());
    }
  }
