@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class RequestValidateHost implements HttpRequestInterceptor {

    public RequestValidateHost() {
        super();
    }

    @Override
    public void process(final HttpRequest request, final EntityDetails entity, final HttpContext context)
            throws HttpException, IOException {
        Args.notNull(request, "HTTP request");

        final Header header = request.getHeader(HttpHeaders.HOST);
        if (header != null) {
            final URIAuthority authority;
            try {
                authority = URIAuthority.create(header.getValue());
            } catch (final URISyntaxException ex) {
                throw new ProtocolException(ex.getMessage(), ex);
            }
            request.setAuthority(authority);
        } else {
            final ProtocolVersion version = request.getVersion() != null ? request.getVersion() : HttpVersion.HTTP_1_1;
            if (version.greaterEquals(HttpVersion.HTTP_1_1)) {
                throw new ProtocolException("Host header is absent");
            }
        }
    }

}
