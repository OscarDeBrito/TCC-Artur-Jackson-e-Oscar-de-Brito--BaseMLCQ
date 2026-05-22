@SuppressWarnings("rawtypes")
public class MultiReducedMetric implements IMetric {
  private Map<String, ReducedMetric> value = new HashMap<>();
  private IReducer reducer;

  public MultiReducedMetric(IReducer reducer) {
    this.reducer = reducer;
  }

  public ReducedMetric scope(String key) {
    ReducedMetric val = value.get(key);
    if (val == null) {
      value.put(key, val = new ReducedMetric(reducer));
    }
    return val;
  }

  public Object getValueAndReset() {
    Map<String, Object> ret = new HashMap<>();
    for (Map.Entry<String, ReducedMetric> e : value.entrySet()) {
      Object val = e.getValue().getValueAndReset();
      if (val != null) {
        ret.put(e.getKey(), val);
      }
    }
    return ret;
  }
}
