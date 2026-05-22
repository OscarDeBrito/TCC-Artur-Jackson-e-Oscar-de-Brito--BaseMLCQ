public class Snippet__3961246 {

    @Override
            public Consumer build(BlockingQueue<JsonNode> queue) {
                return (isResuming)
                        ? new ResumingConsumer(queue, graph, bulkLoadGraph, parseElement, batchSize)
                        : new Consumer(queue, graph, bulkLoadGraph, parseElement, batchSize);
            }

}
