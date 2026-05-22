public class Snippet__9160221 {

    private static String stripNamespace( String name, String namespace )
    	{
    		if ( name == null || namespace == null )
    			return name;

    		String tmpNamespace = StringUtil.extractNamespace( name );
    		if ( tmpNamespace == null )
    			return name;

    		if ( !namespace.equalsIgnoreCase( tmpNamespace ) )
    			return name;

    		return StringUtil.extractName( name );
    	}

}
