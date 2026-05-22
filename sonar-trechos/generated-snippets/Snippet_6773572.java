public class Snippet__6773572 {

    public ListenableFuture<APINodeList<InstagramUser>> executeAsync(Map<String, Object> extraParams) throws APIException {
          return Futures.transform(
            executeAsyncInternal(extraParams),
            new Function<ResponseWrapper, APINodeList<InstagramUser>>() {
               public APINodeList<InstagramUser> apply(ResponseWrapper result) {
                 try {
                   return APIRequestGetInstagramAccounts.this.parseResponse(result.getBody(), result.getHeader());
                 } catch (Exception e) {
                   throw new RuntimeException(e);
                 }
               }
             }
          );
        };

}
