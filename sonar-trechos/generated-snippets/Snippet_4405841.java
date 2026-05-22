public class Snippet__4405841 {

    public boolean equals( Object that )
        {
            // Quick test
            if ( this == that )
            {
                return true;
            }

            if ( that instanceof AclAttributeWrapper )
            {
                AclAttributeWrapper thatInstance = (AclAttributeWrapper)that;

                return aclAttribute.getName().equalsIgnoreCase( thatInstance.aclAttribute.getName() ) && 
                       ( aclAttribute.isAttributeType() && thatInstance.aclAttribute.isAttributeType() ||
                         ( ( aclAttribute.isObjectClass() || aclAttribute.isObjectClassNotAllowed() ) && 
                             ( thatInstance.aclAttribute.isObjectClass() || thatInstance.aclAttribute.isObjectClassNotAllowed() ) ) );
            }
            else
            {
                return false;
            }
        }

}
