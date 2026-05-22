public class Snippet__9009111 {

    @Override
        public Weight createWeight(IndexSearcher searcher, org.apache.lucene.search.ScoreMode scoreMode, float boost) throws IOException {
          SolrRequestInfo info = SolrRequestInfo.getRequestInfo();

          CoreContainer container = info.getReq().getCore().getCoreContainer();

          final SolrCore fromCore = container.getCore(fromIndex);

          if (fromCore == null) {
            throw new SolrException(SolrException.ErrorCode.BAD_REQUEST, "Cross-core join: no such core " + fromIndex);
          }
          RefCounted<SolrIndexSearcher> fromHolder = null;
          fromHolder = fromCore.getRegisteredSearcher();
          final Query joinQuery;
          try {
            joinQuery = JoinUtil.createJoinQuery(fromField, true,
                toField, fromQuery, fromHolder.get(), this.scoreMode);
          } finally {
            fromCore.close();
            fromHolder.decref();
          }
          return joinQuery.rewrite(searcher.getIndexReader()).createWeight(searcher, scoreMode, boost);
        }

}
