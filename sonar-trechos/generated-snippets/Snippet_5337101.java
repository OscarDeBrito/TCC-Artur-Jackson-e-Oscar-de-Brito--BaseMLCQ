public class KerberosHttpClientConfigurer extends HttpClientConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public void configure(DefaultHttpClient httpClient, SolrParams config) {
        super.configure(httpClient, config);
        logger.info("Setting up SPNego auth...");

        //Enable only SPNEGO authentication scheme.
        final AuthSchemeRegistry registry = new AuthSchemeRegistry();
        registry.register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory(true, false));
        httpClient.setAuthSchemes(registry);

        // Get the credentials from the JAAS configuration rather than here
        final Credentials useJaasCreds = new Credentials() {
            public String getPassword() {
                return null;
            }
            public Principal getUserPrincipal() {
                return null;
            }
        };

        final SolrPortAwareCookieSpecFactory cookieFactory = new SolrPortAwareCookieSpecFactory();
        httpClient.getCookieSpecs().register(cookieFactory.POLICY_NAME, cookieFactory);
        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, cookieFactory.POLICY_NAME);
        httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, useJaasCreds);
        httpClient.addRequestInterceptor(bufferedEntityInterceptor);
    }

    // Set a buffered entity based request interceptor
    private HttpRequestInterceptor bufferedEntityInterceptor = new HttpRequestInterceptor() {
        @Override
        public void process(HttpRequest request, HttpContext context) throws HttpException,
                IOException {
            if(request instanceof HttpEntityEnclosingRequest) {
                HttpEntityEnclosingRequest enclosingRequest = ((HttpEntityEnclosingRequest) request);
                HttpEntity requestEntity = enclosingRequest.getEntity();
                enclosingRequest.setEntity(new BufferedHttpEntity(requestEntity));
            }
        }
    };

}
