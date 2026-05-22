public class Snippet__5014423 {

    public Snippet__5014423(XFlattenedColumns flattenedColumns, String table) {
        this.table = table;
        for (XFlattenedColumn column : flattenedColumns.getFlattenedColumn()) {
          XField field = firstNonNull(column.getDimAttribute(), column.getMeasure(), column.getExpression());
          if (field != null) {
            if (!tables.containsKey(field.getClass())) {
              tables.put(field.getClass(), CollectionTableFactory.getCollectionTable(field.getClass(), table));
            }
            tables.get(field.getClass()).getCollection().add(column);
          } else {
            if (column.getChainName() != null) {
              chainNames.add(column.getChainName());
            }
            if (column.getTableName() != null) {
              tableNames.add(column.getTableName());
            }
          }
        }
      }

}
