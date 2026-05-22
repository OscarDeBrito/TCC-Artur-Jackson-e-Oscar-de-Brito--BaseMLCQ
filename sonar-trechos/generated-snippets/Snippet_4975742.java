public class Snippet__4975742 {

    private void to(final TopicNameExtractor<K, V> topicExtractor, final ProducedInternal<K, V> produced) {
            final String name = builder.newProcessorName(SINK_NAME);

            final StreamSinkNode<K, V> sinkNode = new StreamSinkNode<>(
                name,
                topicExtractor,
                produced
            );

            builder.addGraphNode(this.streamsGraphNode, sinkNode);
        }

}
