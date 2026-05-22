public class Snippet__8510687 {

    @DB()
        protected void setField(final Object entity, final ResultSet rs, ResultSetMetaData meta, final int index) throws SQLException {
            Attribute attr = _allColumns.get(new Pair<String, String>(meta.getTableName(index), meta.getColumnName(index)));
            if (attr == null) {
                // work around for mysql bug to return original table name instead of view name in db view case
                Table tbl = entity.getClass().getSuperclass().getAnnotation(Table.class);
                if (tbl != null) {
                    attr = _allColumns.get(new Pair<String, String>(tbl.name(), meta.getColumnLabel(index)));
                }
            }
            assert (attr != null) : "How come I can't find " + meta.getCatalogName(index) + "." + meta.getColumnName(index);
            setField(entity, attr.field, rs, index);
        }

}
