public class HostnameFilter implements Filter {
    private static final XLog LOG = XLog.getLog(HostnameFilter.class);

    static final ThreadLocal<String> HOSTNAME_TL = new ThreadLocal<String>();

    /**
     * Initializes the filter.
     * <p>
     * This implementation is a NOP.
     *
     * @param config filter configuration.
     *
     * @throws ServletException thrown if the filter could not be initialized.
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Resolves the requester hostname and delegates the request to the chain.
     * <p>
     * The requester hostname is available via the {@link #get} method.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @param chain filter chain.
     *
     * @throws IOException thrown if an IO error occurrs.
     * @throws ServletException thrown if a servet error occurrs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        try {
            String hostname;
            try {
                String address = request.getRemoteAddr();
                if (address != null) {
                    hostname = InetAddress.getByName(address).getCanonicalHostName();
                } else {
                    LOG.warn("Request remote address is NULL");
                    hostname = "???";
                }
            } catch (UnknownHostException ex) {
                LOG.warn("Request remote address could not be resolved, {0}", ex.toString(), ex);
                hostname = "???";
            }
            HOSTNAME_TL.set(hostname);
            XLog.Info.get().clear();
            chain.doFilter(request, response);
        }
        finally {
            HOSTNAME_TL.remove();
        }
    }

    /**
     * Returns the requester hostname.
     *
     * @return the requester hostname.
     */
    public static String get() {
        return HOSTNAME_TL.get();
    }

    /**
     * Destroys the filter.
     * <p>
     * This implementation is a NOP.
     */
    @Override
    public void destroy() {
    }
}
