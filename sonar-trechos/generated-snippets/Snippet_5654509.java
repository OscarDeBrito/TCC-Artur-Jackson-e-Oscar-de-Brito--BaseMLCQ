public class Snippet__5654509 {

    public void setAllowedColumns(final Set<Column> inclusion) {
            ArgumentChecks.ensureNonNull("inclusion", inclusion);
            columns.clear();
            columns.addAll(inclusion);
        }

}
