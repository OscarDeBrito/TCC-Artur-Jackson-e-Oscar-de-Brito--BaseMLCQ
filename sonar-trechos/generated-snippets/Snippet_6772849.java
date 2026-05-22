public class Snippet__6772849 {

    public ListenableFuture<APINodeList<Comment>> executeAsync(Map<String, Object> extraParams) throws APIException {
          return Futures.transform(
            executeAsyncInternal(extraParams),
            new Function<ResponseWrapper, APINodeList<Comment>>() {
               public APINodeList<Comment> apply(ResponseWrapper result) {
                 try {
                   return APIRequestGetComments.this.parseResponse(result.getBody(), result.getHeader());
                 } catch (Exception e) {
                   throw new RuntimeException(e);
                 }
               }
             }
          );
        };

}
