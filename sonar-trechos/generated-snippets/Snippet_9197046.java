public class Snippet__9197046 {

    public static boolean isValidReferenceForCompoundElement( Module module,
    			DesignElement element )
    	{
    		ElementRefValue refValue = (ElementRefValue) element.getLocalProperty(
    				module, IDesignElementModel.EXTENDS_PROP );
    		if ( refValue == null )
    			return true;

    		if ( element.getDefn( ).isContainer( ) && !refValue.isResolved( ) )
    			return false;

    		// if any ancestor of this element loses extended element, return false

    		DesignElement parent = element.getExtendsElement( );
    		while ( parent != null )
    		{
    			if ( !isValidReferenceForCompoundElement( parent.getRoot( ), parent ) )
    				return false;
    			parent = parent.getExtendsElement( );
    		}

    		return true;
    	}

}
