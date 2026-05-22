@Path("token")
@ApplicationScoped
public class OAuth2TokenService extends AccessTokenService implements OAuth2Application.Defaults {
    @Inject
    private OAuth2Configurer configurer;

    @Inject
    private LazyImpl delegate;

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    public Response handleTokenRequest(final MultivaluedMap<String, String> params) {
        return getDelegate().handleTokenRequest(params);
    }

    private AccessTokenService getDelegate() {
        delegate.setMessageContext(getMessageContext());
        configurer.accept(delegate);
        return delegate;
    }

    @RequestScoped
    @Typed(LazyImpl.class)
    static class LazyImpl extends AccessTokenService implements OAuth2Application.Defaults {
        @Inject
        private OAuth2Configurer configurer;

        @Override // don't fail without a client
        protected Client getClientFromBasicAuthScheme(final MultivaluedMap<String, String> params) {
            final List<String> authorization = getMessageContext().getHttpHeaders().getRequestHeader("Authorization");
            if (authorization == null || authorization.isEmpty()) {
                if (!configurer.getConfiguration().isForceClient()) {
                    return DEFAULT_CLIENT;
                }
            }
            return super.getClientFromBasicAuthScheme(params);
        }
    }
}
