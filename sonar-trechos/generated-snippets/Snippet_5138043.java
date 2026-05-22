public class Snippet__5138043 {

    public static EarModule newEarModule( Artifact artifact, JavaEEVersion javaEEVersion, String defaultLibBundleDir,
                                              Boolean includeInApplicationXml,
                                              ArtifactTypeMappingService typeMappingService )
            throws UnknownArtifactTypeException
        {
            // Get the standard artifact type based on default config and user-defined mapping(s)
            final String artifactType;
            try
            {
                artifactType = typeMappingService.getStandardType( artifact.getType() );
            }
            catch ( UnknownArtifactTypeException e )
            {
                throw new UnknownArtifactTypeException( e.getMessage() + " for " + artifact.getArtifactId() );
            }

            if ( "jar".equals( artifactType ) )
            {
                return new JarModule( artifact, defaultLibBundleDir, includeInApplicationXml );
            }
            else if ( "ejb".equals( artifactType ) )
            {
                return new EjbModule( artifact );
            }
            else if ( "par".equals( artifactType ) )
            {
                return new ParModule( artifact );
            }
            else if ( "ejb-client".equals( artifactType ) )
            {
                // Somewhat weird way to tackle the problem described in MEAR-85
                if ( javaEEVersion.le( JavaEEVersion.ONE_DOT_FOUR ) )
                {
                    return new EjbClientModule( artifact, null );
                }
                else
                {
                    return new EjbClientModule( artifact, defaultLibBundleDir );
                }
            }
            else if ( "app-client".equals( artifactType ) )
            {
                return new AppClientModule( artifact );
            }
            else if ( "rar".equals( artifactType ) )
            {
                return new RarModule( artifact );
            }
            else if ( "war".equals( artifactType ) )
            {
                return new WebModule( artifact );
            }
            else if ( "sar".equals( artifactType ) )
            {
                return new SarModule( artifact );
            }
            else if ( "wsr".equals( artifactType ) )
            {
                return new WsrModule( artifact );
            }
            else if ( "har".equals( artifactType ) )
            {
                return new HarModule( artifact );
            }
            else
            {
                throw new IllegalStateException( "Could not handle artifact type[" + artifactType + "]" );
            }
        }

}
