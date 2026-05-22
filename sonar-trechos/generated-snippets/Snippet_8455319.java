public final class S3ClientFactory {

    private S3ClientFactory() {
        // Prevent instantiation of this factory class.
        throw new RuntimeException("Do not instantiate a Factory class! Refer to the class "
                                   + "to learn how to properly use this factory implementation.");
    }

    /**
     * Return the correct aws s3 client (based on remote vs local).
     * @param maxConnections max connections
     * @return AWSS3Client
     */
    public static S3Client getAWSS3Client(S3Configuration configuration, int maxConnections) {
        return configuration.isUseIAMCredentials() ? new S3ClientIAMOptimizedImpl(configuration, maxConnections)
                : new S3ClientStandardImpl(configuration, maxConnections);
    }
}
