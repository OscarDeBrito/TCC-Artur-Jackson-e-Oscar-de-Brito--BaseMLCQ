public class Snippet__5303636 {

    public static void getIdColumns(FieldMapping fmd, List cols) {
            Column[] pkCols =  ((ValueMappingImpl)fmd.getValue()).getColumns();
            for (int j = 0; j < pkCols.length; j++) {
                Column newCol = new Column();
                newCol.copy(pkCols[j]);
                cols.add(newCol);
            }
        }

}
