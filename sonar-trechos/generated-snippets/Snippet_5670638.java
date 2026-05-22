@Component
@Service(Object.class)
@Property(name="javax.ws.rs", boolValue=true)
@Path("/ontonet/ontology")
public class ScopeManagerResource extends BaseStanbolResource {

    @SuppressWarnings("unused")
    private Logger log = LoggerFactory.getLogger(getClass());

    /*
     * Placeholder for the ONManager to be fetched from the servlet context.
     */
    @Reference
    protected ScopeManager onm;
    @Reference
    protected TcManager tcManager;

    public ScopeManagerResource() {
    }

    /**
     * RESTful DELETE method that clears the entire scope registry and managed ontology store.
     */
    @DELETE
    public void clearOntologies() {
        // First clear the registry...
        for (Scope scope : onm.getRegisteredScopes())
            onm.deregisterScope(scope);
        // ...then clear the store.
        // TODO : the other way around?
    }

    public Set<Scope> getActiveScopes() {
        return onm.getActiveScopes();
    }

    @GET
    @Produces(TEXT_HTML)
    public Response getHtmlInfo(@Context HttpHeaders headers) {
        ResponseBuilder rb = Response.ok(new Viewable("index", this));
        rb.header(HttpHeaders.CONTENT_TYPE, TEXT_HTML + "; charset=utf-8");
//        addCORSOrigin(servletContext, rb, headers);
        return rb.build();
    }

    /**
     * Default GET method for obtaining the set of (both active and, optionally, inactive) ontology scopes
     * currently registered with this instance of KReS.
     * 
     * @param inactive
     *            if true, both active and inactive scopes will be included. Default is false.
     * @param headers
     *            the HTTP headers, supplied by the REST call.
     * @param servletContext
     *            the servlet context, supplied by the REST call.
     * @return a string representation of the requested scope set, in a format acceptable by the client.
     */
    @GET
    @Produces(value = {RDF_XML, OWL_XML, TURTLE, X_TURTLE, FUNCTIONAL_OWL, MANCHESTER_OWL, RDF_JSON, N3,
                       N_TRIPLE, TEXT_PLAIN})
    public Response getScopeModel(@DefaultValue("false") @QueryParam("with-inactive") boolean inactive,
                                  @Context HttpHeaders headers,
                                  @Context ServletContext servletContext) {
        Set<Scope> scopes = inactive ? onm.getRegisteredScopes() : onm.getActiveScopes();

        OWLOntology ontology = ScopeSetRenderer.getScopes(scopes);

        ResponseBuilder rb = Response.ok(ontology);
        MediaType mediaType = MediaTypeUtil.getAcceptableMediaType(headers, null);
        if (mediaType != null) rb.header(HttpHeaders.CONTENT_TYPE, mediaType);
//        addCORSOrigin(servletContext, rb, headers);
        return rb.build();
    }

    public Set<Scope> getScopes() {
        return onm.getRegisteredScopes();
    }

    @OPTIONS
    public Response handleCorsPreflight(@Context HttpHeaders headers) {
        ResponseBuilder rb = Response.ok();
//        enableCORS(servletContext, rb, headers);
        return rb.build();
    }

}
