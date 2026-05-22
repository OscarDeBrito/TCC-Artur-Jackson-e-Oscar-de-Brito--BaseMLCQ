public class Snippet__9115400 {

    public void parse(  ) throws ParseException
    	{
    		try
    		{
    			parser.open( templateDir );
    		}
    		catch ( FileNotFoundException e )
    		{
    			return;
    		}

    		parseElement( );
    	}

}
