public class Snippet__5141485 {

    private Set<Taglet> collectTaglets()
            throws MavenReportException
        {
            Set<Taglet> result = new LinkedHashSet<>();

            if ( includeDependencySources )
            {
                try
                {
                    resolveDependencyBundles();
                }
                catch ( IOException e )
                {
                    throw new MavenReportException(
                        "Failed to resolve javadoc bundles from dependencies: " + e.getMessage(), e );
                }

                if ( isNotEmpty( dependencyJavadocBundles ) )
                {
                    for ( JavadocBundle bundle : dependencyJavadocBundles )
                    {
                        JavadocOptions options = bundle.getOptions();
                        if ( options != null && isNotEmpty( options.getTaglets() ) )
                        {
                            result.addAll( options.getTaglets() );
                        }
                    }
                }
            }

            if ( taglets != null && taglets.length > 0 )
            {
                result.addAll( Arrays.asList( taglets ) );
            }

            return result;
        }

}
