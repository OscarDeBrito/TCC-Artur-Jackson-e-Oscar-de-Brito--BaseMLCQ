public class BatchGroupAlsoByWindowReshuffleFn<K, V, W extends BoundedWindow>
    extends BatchGroupAlsoByWindowFn<K, V, Iterable<V>> {

  public static boolean isReshuffle(WindowingStrategy<?, ?> strategy) {
    return strategy.getTrigger() instanceof ReshuffleTrigger;
  }

  @Override
  public void processElement(
      KV<K, Iterable<WindowedValue<V>>> element,
      PipelineOptions options,
      StepContext stepContext,
      SideInputReader sideInputReader,
      OutputWindowedValue<KV<K, Iterable<V>>> output)
      throws Exception {
    K key = element.getKey();
    for (WindowedValue<V> item : element.getValue()) {
      output.outputWindowedValue(
          KV.<K, Iterable<V>>of(key, Collections.singletonList(item.getValue())),
          item.getTimestamp(),
          item.getWindows(),
          item.getPane());
    }
  }
}
