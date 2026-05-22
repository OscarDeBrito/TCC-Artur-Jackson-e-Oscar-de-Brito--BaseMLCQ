public class Snippet__4866077 {

    @Override
        public boolean removeCheckpoint(String name) {
            checkNotNull(name);

            // try 5 times
            for (int i = 0; i < 5; i++) {
                if (commitSemaphore.tryAcquire()) {
                    try {
                        refreshHead(true);

                        SegmentNodeState state = head.get();
                        SegmentNodeBuilder builder = state.builder();

                        NodeBuilder cp = builder.child("checkpoints").child(name);
                        if (cp.exists()) {
                            cp.remove();
                            SegmentNodeState newState = builder.getNodeState();
                            if (revisions.setHead(state.getRecordId(), newState.getRecordId())) {
                                refreshHead(false);
                                return true;
                            }
                        }
                    } finally {
                        commitSemaphore.release();
                    }
                }
            }
            return false;
        }

}
