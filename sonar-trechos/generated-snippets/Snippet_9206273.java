public class Snippet__9206273 {

    public IAggrFunction getAggregation( String name )
    	{
    		return name != null ? (IAggrFunction) aggrMap.get( name.toUpperCase( ) )
    				: null;
    	}

}
