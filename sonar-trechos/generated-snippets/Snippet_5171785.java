@Named( "versions" )
@Singleton
public class VersionsMetadataGeneratorFactory
    implements MetadataGeneratorFactory
{

    public MetadataGenerator newInstance( RepositorySystemSession session, InstallRequest request )
    {
        return new VersionsMetadataGenerator( session, request );
    }

    public MetadataGenerator newInstance( RepositorySystemSession session, DeployRequest request )
    {
        return new VersionsMetadataGenerator( session, request );
    }

    public float getPriority()
    {
        return 5;
    }

}
