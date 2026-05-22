public class Snippet__9156673 {

    public Object getProperty( String name )
    	{
    		FactoryPropertyHandle factoryPropHandle = style.getFactoryPropertyHandle( name );
    		if (factoryPropHandle == null)
    			return null;
		
    		return factoryPropHandle.getValue( );
    	}

}
