public class BoostQParserPlugin extends QParserPlugin {
  public static final String NAME = "boost";
  public static String BOOSTFUNC = "b";

  @Override
  public QParser createParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
    return new QParser(qstr, localParams, params, req) {
      QParser baseParser;
      ValueSource vs;
      String b;

      @Override
      public Query parse() throws SyntaxError {
        b = localParams.get(BOOSTFUNC);
        baseParser = subQuery(localParams.get(QueryParsing.V), null);
        Query q = baseParser.getQuery();

        if (b == null) return q;
        Query bq = subQuery(b, FunctionQParserPlugin.NAME).getQuery();
        if (bq instanceof FunctionQuery) {
          vs = ((FunctionQuery)bq).getValueSource();
        } else {
          vs = new QueryValueSource(bq, 0.0f);
        }
        return FunctionScoreQuery.boostByValue(q, vs.asDoubleValuesSource());
      }


      @Override
      public String[] getDefaultHighlightFields() {
        return baseParser.getDefaultHighlightFields();
      }
                                           
      @Override
      public Query getHighlightQuery() throws SyntaxError {
        return baseParser.getHighlightQuery();
      }

      @Override
      public void addDebugInfo(NamedList<Object> debugInfo) {
        // encapsulate base debug info in a sub-list?
        baseParser.addDebugInfo(debugInfo);
        debugInfo.add("boost_str",b);
        debugInfo.add("boost_parsed",vs);
      }
    };
  }

}
