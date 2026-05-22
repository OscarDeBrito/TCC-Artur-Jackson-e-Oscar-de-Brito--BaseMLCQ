public class Min<V extends Number> extends BaseNumberValueOperator<V> implements Unifier<V>
{
  /**
   * Computed low value.
   */
  protected V low;

  // transient field
  protected boolean flag = false;

  /**
   * Input port that takes a number and compares to min and stores the new min.
   */
  public final transient DefaultInputPort<V> data = new DefaultInputPort<V>()
  {
    /**
     * Each tuple is compared to the min and a new min (if so) is stored.
     */
    @Override
    public void process(V tuple)
    {
      Min.this.process(tuple);
    }
  };

  /**
   * Unifier process function.
   */
  @Override
  public void process(V tuple)
  {
    if (!flag) {
      low = tuple;
      flag = true;
    } else if (low.doubleValue() > tuple.doubleValue()) {
      low = tuple;
    }
  }

  /**
   * Min output port.
   */
  public final transient DefaultOutputPort<V> min = new DefaultOutputPort<V>()
  {
    @Override
    public Unifier<V> getUnifier()
    {
      return Min.this;
    }
  };

  /**
   * Emits the max. Override getValue if tuple type is mutable.
   * Clears internal data. Node only works in windowed mode.
   */
  @Override
  public void endWindow()
  {
    if (flag) {
      min.emit(low);
    }
    flag = false;
    low = null;
  }
}
