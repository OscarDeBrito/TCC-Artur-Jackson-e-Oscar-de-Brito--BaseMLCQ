public class Snippet__5437215 {

    public static byte[] getTableKeyFromFullName(String fullTableName) {
            int index = fullTableName.indexOf(QueryConstants.NAME_SEPARATOR);
            if (index < 0) {
                index = fullTableName.indexOf(QueryConstants.NAMESPACE_SEPARATOR);
                if (index < 0) { return getTableKey(null, null, fullTableName); }
            }
            String schemaName = fullTableName.substring(0, index);
            String tableName = fullTableName.substring(index+1);
            return getTableKey(null, schemaName, tableName); 
        }

}
