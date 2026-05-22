public class Snippet__4139640 {

    @Override public ColumnStrategy generationStrategy(RelOptTable table,
            int iColumn) {
          final ModifiableViewTable viewTable =
              table.unwrap(ModifiableViewTable.class);
          assert iColumn < viewTable.columnMapping.size();

          // Use the view constraint to generate the default value if the column is
          // constrained.
          final int mappedOrdinal = viewTable.columnMapping.get(iColumn);
          final RexNode viewConstraint = projectMap.get(mappedOrdinal);
          if (viewConstraint != null) {
            return ColumnStrategy.DEFAULT;
          }

          // Otherwise use the default value of the underlying table.
          final Table schemaTable = viewTable.getTable();
          if (schemaTable instanceof Wrapper) {
            final InitializerExpressionFactory initializerExpressionFactory =
                ((Wrapper) schemaTable).unwrap(InitializerExpressionFactory.class);
            if (initializerExpressionFactory != null) {
              return initializerExpressionFactory.generationStrategy(table,
                  iColumn);
            }
          }
          return super.generationStrategy(table, iColumn);
        }

}
