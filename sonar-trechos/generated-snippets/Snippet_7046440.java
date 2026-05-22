public class Snippet__7046440 {

    private boolean sampleLoadingFinished(Map<TopicPartition, Long> endOffsets) {
          for (Map.Entry<TopicPartition, Long> entry : endOffsets.entrySet()) {
            long position = _consumer.position(entry.getKey());
            if (position < entry.getValue()) {
              LOG.debug("Partition {} is still lagging. Current position: {}, LEO: {}", entry.getKey(),
                        position, entry.getValue());
              return false;
            }
          }
          return true;
        }

}
