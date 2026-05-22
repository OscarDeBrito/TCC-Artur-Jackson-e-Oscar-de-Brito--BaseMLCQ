static class ArtifactRepositoryAdapter
        implements ArtifactRepository
    {

        private final RemoteRepository repository;

        ArtifactRepositoryAdapter( RemoteRepository repository )
        {
            this.repository = repository;
        }

        public String pathOf( org.apache.maven.artifact.Artifact artifact )
        {
            return null;
        }

        public String pathOfRemoteRepositoryMetadata( ArtifactMetadata artifactMetadata )
        {
            return null;
        }

        public String pathOfLocalRepositoryMetadata( ArtifactMetadata metadata, ArtifactRepository repository )
        {
            return null;
        }

        public String getUrl()
        {
            return repository.getUrl();
        }

        public void setUrl( String url )
        {
        }

        public String getBasedir()
        {
            return null;
        }

        public String getProtocol()
        {
            return repository.getProtocol();
        }

        public String getId()
        {
            return repository.getId();
        }

        public void setId( String id )
        {
        }

        public ArtifactRepositoryPolicy getSnapshots()
        {
            return null;
        }

        public void setSnapshotUpdatePolicy( ArtifactRepositoryPolicy policy )
        {
        }

        public ArtifactRepositoryPolicy getReleases()
        {
            return null;
        }

        public void setReleaseUpdatePolicy( ArtifactRepositoryPolicy policy )
        {
        }

        public ArtifactRepositoryLayout getLayout()
        {
            return null;
        }

        public void setLayout( ArtifactRepositoryLayout layout )
        {
        }

        public String getKey()
        {
            return getId();
        }

        public boolean isUniqueVersion()
        {
            return true;
        }

        public boolean isBlacklisted()
        {
            return false;
        }

        public void setBlacklisted( boolean blackListed )
        {
        }

        public org.apache.maven.artifact.Artifact find( org.apache.maven.artifact.Artifact artifact )
        {
            return null;
        }

        public List<String> findVersions( org.apache.maven.artifact.Artifact artifact )
        {
            return Collections.emptyList();
        }

        public boolean isProjectAware()
        {
            return false;
        }

        public void setAuthentication( Authentication authentication )
        {
        }

        public Authentication getAuthentication()
        {
            return null;
        }

        public void setProxy( Proxy proxy )
        {
        }

        public Proxy getProxy()
        {
            return null;
        }

        public List<ArtifactRepository> getMirroredRepositories()
        {
            return Collections.emptyList();
        }

        public void setMirroredRepositories( List<ArtifactRepository> mirroredRepositories )
        {
        }

    }
