public class RequestRetryHttpClientFactory extends DefaultHttpClientFactory {

  @Override
  public DefaultHttpClient create(final HttpMethod method, final URI uri) {
    final HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

      @Override
      public boolean retryRequest(final IOException exception, final int executionCount, final HttpContext context) {
        if (executionCount >= 5) {
          // Do not retry if over max retry count
          return false;
        }
        if (exception instanceof InterruptedIOException) {
          // Timeout
          return false;
        }
        if (exception instanceof UnknownHostException) {
          // Unknown host
          return false;
        }
        if (exception instanceof ConnectException) {
          // Connection refused
          return false;
        }
        if (exception instanceof SSLException) {
          // SSL handshake exception
          return false;
        }
        final HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        if (idempotent) {
          // Retry if the request is considered idempotent 
          return true;
        }
        return false;
      }

    };

    final DefaultHttpClient httpClient = super.create(method, uri);
    httpClient.setHttpRequestRetryHandler(myRetryHandler);
    return httpClient;
  }

}
