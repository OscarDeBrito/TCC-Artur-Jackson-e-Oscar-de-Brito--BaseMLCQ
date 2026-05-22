final class HttpPlaceholderRequest extends BasicHttpRequest {
    static final HttpPlaceholderRequest PLACEHOLDER = new HttpPlaceholderRequest();

    private HttpPlaceholderRequest() {
        super(HttpClientDefinitions.PLACEHOLDER_METHOD_NAME,
                HttpClientDefinitions.PLACEHOLDER_URI_VALUE,
                new ProtocolVersion("HTTP", 1, 1));
    }

    static HttpRequest resolveHttpRequest(Object... args) {
        if ((args == null) || (args.length <= 0)) {
            return PLACEHOLDER;
        }

        for (Object argVal : args) {
            if (argVal instanceof HttpRequest) {
                return (HttpRequest) argVal;
            }
        }

        // no match found
        return PLACEHOLDER;
    }
}
