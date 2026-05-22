public class Snippet__5297372 {

    private static int getPageRowCountLimit(Configuration conf) {
        return conf.getInt(PAGE_ROW_COUNT_LIMIT, ParquetProperties.DEFAULT_PAGE_ROW_COUNT_LIMIT);
      }

}
