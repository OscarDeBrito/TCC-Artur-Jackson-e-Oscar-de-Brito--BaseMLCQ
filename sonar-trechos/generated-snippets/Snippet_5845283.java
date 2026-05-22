public class Snippet__5845283 {

    private static void orderFragments(Set<WebXml> orderedFragments,
                Set<WebXml> unordered) {
            Set<WebXml> addedThisRound = new HashSet<>();
            Set<WebXml> addedLastRound = new HashSet<>();
            while (unordered.size() > 0) {
                Iterator<WebXml> source = unordered.iterator();
                while (source.hasNext()) {
                    WebXml fragment = source.next();
                    for (WebXml toRemove : addedLastRound) {
                        fragment.getAfterOrdering().remove(toRemove.getName());
                    }
                    if (fragment.getAfterOrdering().isEmpty()) {
                        addedThisRound.add(fragment);
                        orderedFragments.add(fragment);
                        source.remove();
                    }
                }
                if (addedThisRound.size() == 0) {
                    // Circular
                    throw new IllegalArgumentException(
                            sm.getString("webXml.mergeConflictOrder"));
                }
                addedLastRound.clear();
                addedLastRound.addAll(addedThisRound);
                addedThisRound.clear();
            }
        }

}
