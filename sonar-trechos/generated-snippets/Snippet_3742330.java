public class Snippet__3742330 {

    private static void makeAllColumnsPrimaryKeysIfNoPrimaryKeysFound(Table table) {
            if ((table != null) && (table.getPrimaryKeyColumns() != null) && (table.getPrimaryKeyColumns().length == 0)) {
                Column[] allCoumns = table.getColumns();

                for (Column column : allCoumns) {
                    column.setPrimaryKey(true);
                }
            }
        }

}
