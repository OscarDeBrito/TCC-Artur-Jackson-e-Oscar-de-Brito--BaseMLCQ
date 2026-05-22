public class Snippet__7748135 {

    private boolean containsDownEntity(Set<Entity> seeds) {
                for (Entity seed : seeds) {
                    if (!isViableSeed(seed)) {
                        return true;
                    }
                }
                return false;
            }

}
