public class Snippet__8432784 {

    public Snippet__8432784(RedisClient redisClient, ExchangeConverter exchangeConverter) {
            this.processors.putAll(new ConnectionRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new HashesRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new KeysRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new ListsRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new PubSubRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new SetsRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new SortedSetsRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new StringsRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new TransactionRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
            this.processors.putAll(new GeoRedisProcessorsCreator().getProcessors(redisClient, exchangeConverter));
        }

}
