public class Snippet__4222354 {

    public Builder add(TableMetadata table)
            {
                tables.put(table.name, table);

                tablesById.put(table.id, table);

                table.indexes
                     .stream()
                     .filter(i -> !i.isCustom())
                     .map(i -> CassandraIndex.indexCfsMetadata(table, i))
                     .forEach(i -> indexTables.put(i.indexName().get(), i));

                return this;
            }

}
