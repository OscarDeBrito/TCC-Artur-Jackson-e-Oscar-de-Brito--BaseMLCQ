public abstract class FilterOperator extends BaseOperator
{
  /**
   * This is the input port on which tuples are received.
   */
  @InputPortFieldAnnotation(optional = false)
  public final transient DefaultInputPort<Object> in = new DefaultInputPort<Object>()
  {
    @Override
    public void process(Object tuple)
    {
      if (satisfiesFilter(tuple)) {
        out.emit(tuple);
      }
    }

  };

  /**
   * This is the output port, which emits tuples that satisfy the filter.
   */
  @OutputPortFieldAnnotation(optional = false)
  public final transient DefaultOutputPort<Object> out = new DefaultOutputPort<Object>();

  public abstract boolean satisfiesFilter(Object tuple);
}
