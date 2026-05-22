public class Snippet__5384416 {

    @Override
            public Relationship mapToRelationship(final ProcessContext context, final FlowFile flowFile) {
                final List<Relationship> relationshipList = DistributeLoad.this.weightedRelationshipListRef.get();
                final int numRelationships = relationshipList.size();

                // create a HashSet that contains all of the available relationships, as calling #contains on HashSet
                // is much faster than calling it on a List
                boolean foundFreeRelationship = false;
                Relationship relationship = null;

                int attempts = 0;
                while (!foundFreeRelationship) {
                    final long counterValue = counter.getAndIncrement();
                    final int idx = (int) (counterValue % numRelationships);
                    relationship = relationshipList.get(idx);
                    foundFreeRelationship = context.getAvailableRelationships().contains(relationship);
                    if (++attempts % numRelationships == 0 && !foundFreeRelationship) {
                        return null;
                    }
                }

                return relationship;
            }

}
