public interface ServiceNameResolver {

    /**
     * Resolve pulsar service url.
     *
     * @return resolve the service url to return a socket address
     */
    InetSocketAddress resolveHost();

    /**
     * Resolve pulsar service url
     * @return
     */
    URI resolveHostUri();

    /**
     * Get service url.
     *
     * @return service url
     */
    String getServiceUrl();

    /**
     * Get service uri.
     *
     * @return service uri
     */
    ServiceURI getServiceUri();

    /**
     * Update service url.
     *
     * @param serviceUrl service url
     */
    void updateServiceUrl(String serviceUrl) throws InvalidServiceURL;

}
