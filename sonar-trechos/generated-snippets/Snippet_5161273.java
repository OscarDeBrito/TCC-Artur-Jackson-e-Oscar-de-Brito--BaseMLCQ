public class Snippet__5161273 {

    public Snippet__5161273(GTScanRequest scanRequest, IPartitionStreamer partitionStreamer,
                StorageContext context) {
            this.info = scanRequest.getInfo();
            this.partitionStreamer = partitionStreamer;
            this.blocks = partitionStreamer.asByteArrayIterator();
            this.columns = scanRequest.getColumns();
            this.groupByDims = scanRequest.getAggrGroupBy();
            this.needSorted = (context.getFinalPushDownLimit() != Integer.MAX_VALUE) || context.isStreamAggregateEnabled();
        }

}
