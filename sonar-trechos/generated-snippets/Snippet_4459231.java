public interface MessageReader extends Closeable {

  public void init(DrillBuf buf, List<SchemaPath> columns, VectorContainerWriter writer, boolean allTextMode,
      boolean readNumbersAsDouble);

  public void readMessage(ConsumerRecord<?, ?> message);

  public void ensureAtLeastOneField();

  public KafkaConsumer<byte[], byte[]> getConsumer(KafkaStoragePlugin plugin);
}
