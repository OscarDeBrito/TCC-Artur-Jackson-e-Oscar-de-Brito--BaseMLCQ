public class ServletEndpointDiscoverer
		extends EndpointDiscoverer<ExposableServletEndpoint, Operation>
		implements ServletEndpointsSupplier {

	private final List<PathMapper> endpointPathMappers;

	/**
	 * Create a new {@link ServletEndpointDiscoverer} instance.
	 * @param applicationContext the source application context
	 * @param endpointPathMappers the endpoint path mappers
	 * @param filters filters to apply
	 */
	public ServletEndpointDiscoverer(ApplicationContext applicationContext,
			List<PathMapper> endpointPathMappers,
			Collection<EndpointFilter<ExposableServletEndpoint>> filters) {
		super(applicationContext, ParameterValueMapper.NONE, Collections.emptyList(),
				filters);
		this.endpointPathMappers = endpointPathMappers;
	}

	@Override
	protected boolean isEndpointExposed(Object endpointBean) {
		Class<?> type = ClassUtils.getUserClass(endpointBean.getClass());
		return AnnotatedElementUtils.isAnnotated(type, ServletEndpoint.class);
	}

	@Override
	protected ExposableServletEndpoint createEndpoint(Object endpointBean, EndpointId id,
			boolean enabledByDefault, Collection<Operation> operations) {
		String rootPath = PathMapper.getRootPath(this.endpointPathMappers, id);
		return new DiscoveredServletEndpoint(this, endpointBean, id, rootPath,
				enabledByDefault);
	}

	@Override
	protected Operation createOperation(EndpointId endpointId,
			DiscoveredOperationMethod operationMethod, OperationInvoker invoker) {
		throw new IllegalStateException("ServletEndpoints must not declare operations");
	}

	@Override
	protected OperationKey createOperationKey(Operation operation) {
		throw new IllegalStateException("ServletEndpoints must not declare operations");
	}

}
