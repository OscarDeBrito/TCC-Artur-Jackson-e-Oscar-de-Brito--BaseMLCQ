public final class CouchbaseClusterUtil {
    private CouchbaseClusterUtil() {
    }

    public static CouchbaseCluster getCouchbaseCluster(String database, CouchbaseCluster couchbaseCluster, String user, String password) {
        Objects.requireNonNull(database, "database is required");
        CouchbaseCluster authenticate = couchbaseCluster.authenticate(user, password);

       /*
        ClusterManager clusterManager = authenticate.clusterManager();
        List<BucketSettings> buckets = clusterManager.getBuckets();
        if(buckets.stream().noneMatch(b -> b.name().equals(database))) {
            BucketSettings bucketSettings =  DefaultBucketSettings.builder().name(database).quota(100);
            clusterManager.insertBucket(bucketSettings);
            Bucket bucket = authenticate.openBucket(database);
            bucket.query(N1qlQuery.simple("CREATE PRIMARY INDEX index_" + database + " on " + database));
            bucket.close();
        }*/
        return authenticate;
    }

}
