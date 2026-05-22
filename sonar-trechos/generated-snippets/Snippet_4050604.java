private static class NonMergingWindowFnRunner<T, W extends BoundedWindow>
      extends WindowMergingFnRunner<T, W> {
    @Override
    KV<T, KV<Iterable<W>, Iterable<KV<W, Iterable<W>>>>> mergeWindows(
        KV<T, Iterable<W>> windowsToMerge) {
      return KV.of(
          windowsToMerge.getKey(), KV.of(windowsToMerge.getValue(), Collections.emptyList()));
    }
  }
