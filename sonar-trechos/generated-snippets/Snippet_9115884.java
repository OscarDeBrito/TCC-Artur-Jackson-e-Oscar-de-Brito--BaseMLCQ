public class Snippet__9115884 {

    private ColorHandle doGetColorHandle( String memberName )
    	{
    		return new ColorHandle( getElementHandle( ), StructureContextUtil
    				.createStructureContext( this, memberName ) );
    	}

}
