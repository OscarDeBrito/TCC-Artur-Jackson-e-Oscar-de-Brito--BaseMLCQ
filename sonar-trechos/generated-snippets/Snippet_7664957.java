@Deprecated
public class MarshallingMethodEndpointAdapter extends AbstractMethodEndpointAdapter implements InitializingBean {

	private Marshaller marshaller;

	private Unmarshaller unmarshaller;

	/**
	 * Creates a new {@code MarshallingMethodEndpointAdapter}. The {@link Marshaller} and {@link Unmarshaller} must
	 * be injected using properties.
	 *
	 * @see #setMarshaller(org.springframework.oxm.Marshaller)
	 * @see #setUnmarshaller(org.springframework.oxm.Unmarshaller)
	 */
	public MarshallingMethodEndpointAdapter() {
	}

	/**
	 * Creates a new {@code MarshallingMethodEndpointAdapter} with the given marshaller. If the given {@link
	 * Marshaller} also implements the {@link Unmarshaller} interface, it is used for both marshalling and
	 * unmarshalling. Otherwise, an exception is thrown.
	 *
	 * <p>Note that all {@link Marshaller} implementations in Spring also implement the {@link Unmarshaller} interface,
	 * so that you can safely use this constructor.
	 *
	 * @param marshaller object used as marshaller and unmarshaller
	 * @throws IllegalArgumentException when {@code marshaller} does not implement the {@link Unmarshaller}
	 *									interface
	 */
	public MarshallingMethodEndpointAdapter(Marshaller marshaller) {
		Assert.notNull(marshaller, "marshaller must not be null");
		if (!(marshaller instanceof Unmarshaller)) {
			throw new IllegalArgumentException("Marshaller [" + marshaller + "] does not implement the Unmarshaller " +
					"interface. Please set an Unmarshaller explicitly by using the " +
					"MarshallingMethodEndpointAdapter(Marshaller, Unmarshaller) constructor.");
		}
		else {
			this.setMarshaller(marshaller);
			this.setUnmarshaller((Unmarshaller) marshaller);
		}
	}

	/**
	 * Creates a new {@code MarshallingMethodEndpointAdapter} with the given marshaller and unmarshaller.
	 *
	 * @param marshaller   the marshaller to use
	 * @param unmarshaller the unmarshaller to use
	 */
	public MarshallingMethodEndpointAdapter(Marshaller marshaller, Unmarshaller unmarshaller) {
		Assert.notNull(marshaller, "marshaller must not be null");
		Assert.notNull(unmarshaller, "unmarshaller must not be null");
		this.setMarshaller(marshaller);
		this.setUnmarshaller(unmarshaller);
	}

	/** Returns the marshaller used for transforming objects into XML. */
	public Marshaller getMarshaller() {
		return marshaller;
	}

	/** Sets the marshaller used for transforming objects into XML. */
	public final void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/** Returns the unmarshaller used for transforming XML into objects. */
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	/** Sets the unmarshaller used for transforming XML into objects. */
	public final void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(getMarshaller(), "marshaller is required");
		Assert.notNull(getUnmarshaller(), "unmarshaller is required");
	}

	@Override
	protected void invokeInternal(MessageContext messageContext, MethodEndpoint methodEndpoint) throws Exception {
		WebServiceMessage request = messageContext.getRequest();
		Object requestObject = unmarshalRequest(request);
		Object responseObject = methodEndpoint.invoke(new Object[]{requestObject});
		if (responseObject != null) {
			WebServiceMessage response = messageContext.getResponse();
			marshalResponse(responseObject, response);
		}
	}

	private Object unmarshalRequest(WebServiceMessage request) throws IOException {
		Object requestObject = MarshallingUtils.unmarshal(getUnmarshaller(), request);
		if (logger.isDebugEnabled()) {
			logger.debug("Unmarshalled payload request to [" + requestObject + "]");
		}
		return requestObject;
	}

	private void marshalResponse(Object responseObject, WebServiceMessage response) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Marshalling [" + responseObject + "] to response payload");
		}
		MarshallingUtils.marshal(getMarshaller(), responseObject, response);
	}

	/**
	 * Supports a method with a single, unmarshallable parameter, and that return {@code void} or a marshallable
	 * type.
	 *
	 * @see Marshaller#supports(Class)
	 * @see Unmarshaller#supports(Class)
	 */
	@Override
	protected boolean supportsInternal(MethodEndpoint methodEndpoint) {
		Method method = methodEndpoint.getMethod();
		return supportsReturnType(method) && supportsParameters(method);
	}

	private boolean supportsReturnType(Method method) {
		return (Void.TYPE.equals(method.getReturnType()) || getMarshaller().supports(method.getReturnType()));
	}

	private boolean supportsParameters(Method method) {
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		else {
			return getUnmarshaller().supports(method.getParameterTypes()[0]);
		}
	}
}
