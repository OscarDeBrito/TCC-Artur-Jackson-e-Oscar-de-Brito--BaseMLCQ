public class RegexIdentityAssertionFilter extends
    CommonIdentityAssertionFilter {

  private String input;
  private String output;
  private Map<String,String> dict;
  RegexTemplate template;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    super.init(filterConfig);
    try {
      input = filterConfig.getInitParameter( "input" );
      if( input == null ) {
        input = "";
      }
      output = filterConfig.getInitParameter( "output" );
      if( output == null ) {
        output = "";
      }
      dict = loadDictionary( filterConfig.getInitParameter( "lookup" ) );
      boolean useOriginalOnLookupFailure = Boolean.parseBoolean(filterConfig.getInitParameter("use.original.on.lookup.failure"));
      template = new RegexTemplate( input, output, dict, useOriginalOnLookupFailure);
    } catch ( PrincipalMappingException e ) {
      throw new ServletException( e );
    }
  }

  @Override
  public String[] mapGroupPrincipals(String mappedPrincipalName, Subject subject) {
    // Returning null will allow existing Subject group principals to remain the same
    return null;
  }

  @Override
  public String mapUserPrincipal(String principalName) {
    return template.apply( principalName );
  }

  private Map<String, String> loadDictionary( String config ) throws PrincipalMappingException {
    Map<String,String> dict = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    if( config != null && !config.isEmpty() ) {
      try {
        StringTokenizer t = new StringTokenizer( config, ";" );
        while( t.hasMoreTokens() ) {
          String nvp = t.nextToken();
          String[] a = nvp.split( "=" );
          dict.put( a[0].trim(), a[1].trim() );
        }
        return dict;
      } catch( Exception e ) {
        dict.clear();
        throw new PrincipalMappingException(
            "Unable to load lookup dictionary from provided configuration: " + config +
                ".  No principal mapping will be provided.", e );
      }
    }
    return dict;
  }

}
