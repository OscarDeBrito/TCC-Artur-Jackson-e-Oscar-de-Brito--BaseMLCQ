public interface HbaseEventSerializer extends Configurable, ConfigurableComponent {
  /**
   * Initialize the event serializer.
   * @param event Event to be written to HBase
   * @param columnFamily Column family to write to
   */
  public void initialize(Event event, byte[] columnFamily);

  /**
   * Get the actions that should be written out to hbase as a result of this
   * event. This list is written to hbase using the HBase batch API.
   * @return List of {@link org.apache.hadoop.hbase.client.Row} which
   * are written as such to HBase.
   *
   * 0.92 increments do not implement Row, so this is not generic.
   *
   */
  public List<Row> getActions();

  public List<Increment> getIncrements();

  /*
   * Clean up any state. This will be called when the sink is being stopped.
   */
  public void close();
}
