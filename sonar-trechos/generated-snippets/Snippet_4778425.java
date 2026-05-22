public class Snippet__4778425 {

    @Override
      public UpdateIndexAuthorizationProcessor getInstance(SolrQueryRequest req,
          SolrQueryResponse rsp, UpdateRequestProcessor next) {
        return new UpdateIndexAuthorizationProcessor(req, rsp, next);
      }

}
