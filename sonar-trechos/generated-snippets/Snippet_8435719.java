public class HttpRestHeaderFilterStrategy extends HttpHeaderFilterStrategy {

    private final String templateUri;
    private final String queryParameters;

    public HttpRestHeaderFilterStrategy(String templateUri, String queryParameters) {
        super();
        this.templateUri = templateUri;
        this.queryParameters = queryParameters;
    }

    @Override
    public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
        boolean answer = super.applyFilterToCamelHeaders(headerName, headerValue, exchange);
        // using rest producer then headers are mapping to uri and query parameters using {key} syntax
        // if there is a match to an existing Camel Message header, then we should filter (=true) this
        // header as its already been mapped by the RestProducer from camel-core, and we do not want
        // the header to included as HTTP header also (eg as duplicate value)
        if (!answer) {
            if (templateUri != null) {
                String token = "{" + headerName + "}";
                if (templateUri.contains(token)) {
                    answer = true;
                }
            }
            if (!answer && queryParameters != null) {
                String token = "=%7B" + headerName + "%7D"; // encoded values for { }
                if (queryParameters.contains(token)) {
                    answer = true;
                }
            }
        }
        return answer;
    }

}
