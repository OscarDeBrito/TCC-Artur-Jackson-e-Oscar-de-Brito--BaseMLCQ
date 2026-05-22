public class Snippet__4276865 {

    private T getPointFromLargestNumberCluster(final Collection<? extends Cluster<T>> clusters)
                throws ConvergenceException {

            int maxNumber = 0;
            Cluster<T> selected = null;
            for (final Cluster<T> cluster : clusters) {

                // get the number of points of the current cluster
                final int number = cluster.getPoints().size();

                // select the cluster with the largest number of points
                if (number > maxNumber) {
                    maxNumber = number;
                    selected = cluster;
                }

            }

            // did we find at least one non-empty cluster ?
            if (selected == null) {
                throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS);
            }

            // extract a random point from the cluster
            final List<T> selectedPoints = selected.getPoints();
            return selectedPoints.remove(random.nextInt(selectedPoints.size()));

        }

}
