public class Snippet__5651881 {

    @Override
      public Map<String, List<Integer>> getColumnInfoForQuery(String query) {
        // Manipulate the query to return immediately, with zero rows.
        String rawQuery = query.replace(SUBSTITUTE_TOKEN, " (1 = 0) ");
        return getColumnInfoForRawQuery(rawQuery);
      }

}
