public class Snippet__7141504 {

    @Deprecated
      @Override
      public <T> void sendRestRequest(final Request<T> request, RequestContext requestContext,
          Callback<RestResponse> callback) {
        if (_restClient == null) {
          throw new UnsupportedOperationException("sendRestRequest is not supported by the ForwardingRestClient");
        }
        _restClient.sendRestRequest(request, requestContext, callback);
      }

}
