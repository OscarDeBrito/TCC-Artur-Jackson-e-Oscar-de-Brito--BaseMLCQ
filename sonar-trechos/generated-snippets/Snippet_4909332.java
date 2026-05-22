public class Snippet__4909332 {

    private boolean tryCommit() {
            try {
                NodeState newRoot = nodeStore.merge(rootBuilder, EmptyHook.INSTANCE, CommitInfo.EMPTY);
                totalMigratedNodes += migratedNodes;
                log.info("{} nodes merged succesfully. Nodes migrated in this session: {}", migratedNodes, totalMigratedNodes);
                lastCommit = System.currentTimeMillis();
                migratedNodes = 0;

                rootBuilder = newRoot.builder();
                nodeIterator = nodeIterator.switchRoot(newRoot);

                return true;
            } catch (CommitFailedException e) {
                log.error("Can't commit. Resetting the migrator", e);
                refreshAndReset(nodeStore.getRoot());
                return false;
            }
        }

}
