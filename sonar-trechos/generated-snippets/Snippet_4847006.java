public class Snippet__4847006 {

    public Snippet__4847006(String jcrServerURI, String defaultWorkspaceName,
                                     BatchReadConfig batchReadConfig, int itemInfoCacheSize,
                                     int maximumHttpConnections) throws RepositoryException {

            super(jcrServerURI, IdFactoryImpl.getInstance(), NameFactoryImpl.getInstance(),
                    PathFactoryImpl.getInstance(), new QValueFactoryImpl(), itemInfoCacheSize, maximumHttpConnections);

            try {
                URI repositoryUri = computeRepositoryUri(jcrServerURI);
                this.jcrServerURI = repositoryUri.toString();
            } catch (URISyntaxException e) {
                throw new RepositoryException(e);
            }

            this.defaultWorkspaceName = defaultWorkspaceName;
            if (batchReadConfig == null) {
                this.batchReadConfig = new BatchReadConfig() {
                    public int getDepth(Path path, PathResolver resolver) {
                        return 0;
                    }
                };
            } else {
                this.batchReadConfig = batchReadConfig;
            }
        }

}
