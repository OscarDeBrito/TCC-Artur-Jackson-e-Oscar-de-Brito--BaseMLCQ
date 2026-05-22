public class Snippet__4980296 {

    @Override
        public String toString() {
            return String.format("Partition(topic = %s, partition = %d, leader = %s, replicas = %s, isr = %s, offlineReplicas = %s)",
                                 topic,
                                 partition,
                                 leader == null ? "none" : leader.idString(),
                                 formatNodeIds(replicas),
                                 formatNodeIds(inSyncReplicas),
                                 formatNodeIds(offlineReplicas));
        }

}
