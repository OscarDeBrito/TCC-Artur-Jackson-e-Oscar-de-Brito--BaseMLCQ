public class Snippet__4960946 {

    boolean updateNewAndRestoringTasks() {
            active.initializeNewTasks();
            standby.initializeNewTasks();

            final Collection<TopicPartition> restored = changelogReader.restore(active);

            active.updateRestored(restored);

            if (active.allTasksRunning()) {
                final Set<TopicPartition> assignment = consumer.assignment();
                log.trace("Resuming partitions {}", assignment);
                consumer.resume(assignment);
                assignStandbyPartitions();
                return true;
            }
            return false;
        }

}
