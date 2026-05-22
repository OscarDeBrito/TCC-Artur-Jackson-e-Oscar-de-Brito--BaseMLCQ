public abstract class IndexLogReader implements LogReader {

    // TODO: Work around https://issues.apache.org/jira/browse/HBASE-2198. More graceful implementation should
    // use SingleColumnValueExcludeFilter,
    // but it's complicated in current implementation.
    protected static void workaroundHBASE2198(Get get, Filter filter, byte[][] qualifiers) {
        if (filter instanceof SingleColumnValueFilter) {
            if (qualifiers == null) {
                get.addFamily(((SingleColumnValueFilter)filter).getFamily());
            } else {
                get.addColumn(((SingleColumnValueFilter)filter).getFamily(),
                              ((SingleColumnValueFilter)filter).getQualifier());
            }
            return;
        }
        if (filter instanceof FilterList) {
            for (Filter f : ((FilterList)filter).getFilters()) {
                workaroundHBASE2198(get, f, qualifiers);
            }
        }
    }

}
