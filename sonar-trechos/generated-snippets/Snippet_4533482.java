public class DatatableData {

    @SuppressWarnings("unused")
    private final String applicationTableName;
    @SuppressWarnings("unused")
    private final String registeredTableName;
    @SuppressWarnings("unused")
    private final List<ResultsetColumnHeaderData> columnHeaderData;


    public static DatatableData create(final String applicationTableName, final String registeredTableName,
            final List<ResultsetColumnHeaderData> columnHeaderData) {
        return new DatatableData(applicationTableName, registeredTableName, columnHeaderData);
    }

    private DatatableData(final String applicationTableName, final String registeredTableName,
            final List<ResultsetColumnHeaderData> columnHeaderData) {
        this.applicationTableName = applicationTableName;
        this.registeredTableName = registeredTableName;
        this.columnHeaderData = columnHeaderData;

    }

    public boolean hasColumn(final String columnName){

        for(ResultsetColumnHeaderData c : this.columnHeaderData){

            if(c.getColumnName().equals(columnName)) return true;
        }

        return false;
    }

    public String getRegisteredTableName(){
        return registeredTableName;
    }
    
}
