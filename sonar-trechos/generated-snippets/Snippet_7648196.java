public class ServiceAuthenticationDetailsSource implements
		AuthenticationDetailsSource<HttpServletRequest, ServiceAuthenticationDetails> {
	// ~ Instance fields
	// ================================================================================================

	private final Pattern artifactPattern;

	private ServiceProperties serviceProperties;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Creates an implementation that uses the specified ServiceProperites and the default
	 * CAS artifactParameterName.
	 *
	 * @param serviceProperties The ServiceProperties to use to construct the serviceUrl.
	 */
	public ServiceAuthenticationDetailsSource(ServiceProperties serviceProperties) {
		this(serviceProperties, ServiceProperties.DEFAULT_CAS_ARTIFACT_PARAMETER);
	}

	/**
	 * Creates an implementation that uses the specified artifactParameterName
	 *
	 * @param serviceProperties The ServiceProperties to use to construct the serviceUrl.
	 * @param artifactParameterName the artifactParameterName that is removed from the
	 * current URL. The result becomes the service url. Cannot be null and cannot be an
	 * empty String.
	 */
	public ServiceAuthenticationDetailsSource(ServiceProperties serviceProperties,
			String artifactParameterName) {
		Assert.notNull(serviceProperties, "serviceProperties cannot be null");
		this.serviceProperties = serviceProperties;
		this.artifactPattern = DefaultServiceAuthenticationDetails
				.createArtifactPattern(artifactParameterName);
	}

	// ~ Methods
	// ========================================================================================================

	/**
	 * @param context the {@code HttpServletRequest} object.
	 * @return the {@code ServiceAuthenticationDetails} containing information about the
	 * current request
	 */
	public ServiceAuthenticationDetails buildDetails(HttpServletRequest context) {
		try {
			return new DefaultServiceAuthenticationDetails(
					serviceProperties.getService(), context, artifactPattern);
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
