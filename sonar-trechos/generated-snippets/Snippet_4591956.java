public class Snippet__4591956 {

    private Scanner createScanner(Query<K,T> query) throws TableNotFoundException {
        // TODO make isolated scanner optional?
        Scanner scanner = new IsolatedScanner(conn.createScanner(mapping.tableName, Authorizations.EMPTY));
        setFetchColumns(scanner, query.getFields());

        scanner.setRange(createRange(query));

        if (query.getStartTime() != -1 || query.getEndTime() != -1) {
          IteratorSetting is = new IteratorSetting(30, TimestampFilter.class);
          if (query.getStartTime() != -1)
            TimestampFilter.setStart(is, query.getStartTime(), true);
          if (query.getEndTime() != -1)
            TimestampFilter.setEnd(is, query.getEndTime(), true);

          scanner.addScanIterator(is);
        }

        return scanner;
      }

}
