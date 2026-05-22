public class ShardManager
{

  protected final Map<String, String> shardPos = new HashMap<String, String>();
  /**
   * Load initial positions for all kinesis Shards
   * The method is called at the first attempt of creating shards and the return value is used as initial positions for simple consumer
   *
   * @return Map of Kinesis shard id as key and sequence id as value
   */
  public Map<String, String> loadInitialShardPositions()
  {
    return shardPos;
  }

  /**
   * @param shardPositions positions for specified shards, it is reported by individual operator instances
   * The method is called every AbstractPartitionableKinesisInputOperator.getRepartitionCheckInterval() to update the current positions
   */
  public void updatePositions(Map<String, String> shardPositions)
  {
    shardPos.putAll(shardPositions);
  }
}
