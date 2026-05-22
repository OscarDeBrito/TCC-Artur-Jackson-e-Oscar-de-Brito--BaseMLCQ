public class DefaultHttpResponseParser<T extends HttpResponse> extends AbstractMessageParser<T> {

    private final HttpResponseFactory<T> responseFactory;

    /**
     * Creates an instance of DefaultHttpResponseParser.
     *
     * @param responseFactory the response factory.
     * @param parser the line parser. If {@code null}
     *   {@link org.apache.hc.core5.http.message.LazyLineParser#INSTANCE} will be used.
     * @param h1Config Message h1Config. If {@code null}
     *   {@link H1Config#DEFAULT} will be used.
     *
     * @since 4.3
     */
    public DefaultHttpResponseParser(
            final HttpResponseFactory<T> responseFactory,
            final LineParser parser,
            final H1Config h1Config) {
        super(parser, h1Config);
        this.responseFactory = Args.notNull(responseFactory, "Response factory");
    }

    /**
     * @since 4.3
     */
    public DefaultHttpResponseParser(final HttpResponseFactory<T> responseFactory, final H1Config h1Config) {
        this(responseFactory, null, h1Config);
    }

    /**
     * @since 4.3
     */
    public DefaultHttpResponseParser(final HttpResponseFactory<T> responseFactory) {
        this(responseFactory, null);
    }

    @Override
    protected T createMessage(final CharArrayBuffer buffer) throws HttpException {
        final StatusLine statusLine = getLineParser().parseStatusLine(buffer);
        final T response = this.responseFactory.newHttpResponse(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        response.setVersion(statusLine.getProtocolVersion());
        return response;
    }

}
