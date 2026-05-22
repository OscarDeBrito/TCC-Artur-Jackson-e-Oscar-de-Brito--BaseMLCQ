public class Snippet__6465751 {

    private void processImportBundle ( final BundleInformation result, final Attributes ma )
        {
            for ( final AttributedValue av : emptyNull ( Headers.parseList ( ma.getValue ( Constants.REQUIRE_BUNDLE ) ) ) )
            {
                final String name = av.getValue ();
                final String vs = av.getAttributes ().get ( "bundle-version" );
                VersionRange vr = null;
                if ( vs != null )
                {
                    vr = new VersionRange ( vs );
                }
                final boolean optional = "optional".equals ( av.getAttributes ().get ( "resolution" ) );
                final boolean reexport = "reexport".equals ( av.getAttributes ().get ( "visibility" ) );
                result.getBundleRequirements ().add ( new BundleRequirement ( name, vr, optional, reexport ) );
            }
        }

}
