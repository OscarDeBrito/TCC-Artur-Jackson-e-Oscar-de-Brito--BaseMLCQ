public class Snippet__4318095 {

    public final Dependable getDependable(DataDictionary dd, UUID dependableObjectID)
    		throws StandardException
    	{
            Dependable dependable = findDependable(dd, dependableObjectID);
            if (dependable == null)
                throw StandardException.newException(SQLState.LANG_OBJECT_NOT_FOUND,
                        getSQLObjectType(), dependableObjectID);
            return dependable;
        }

}
