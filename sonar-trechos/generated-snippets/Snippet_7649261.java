public class PortMapperImpl implements PortMapper {
	// ~ Instance fields
	// ================================================================================================

	private final Map<Integer, Integer> httpsPortMappings;

	// ~ Constructors
	// ===================================================================================================

	public PortMapperImpl() {
		this.httpsPortMappings = new HashMap<>();
		this.httpsPortMappings.put(Integer.valueOf(80), Integer.valueOf(443));
		this.httpsPortMappings.put(Integer.valueOf(8080), Integer.valueOf(8443));
	}

	// ~ Methods
	// ========================================================================================================

	/**
	 * Returns the translated (Integer -&gt; Integer) version of the original port mapping
	 * specified via setHttpsPortMapping()
	 */
	public Map<Integer, Integer> getTranslatedPortMappings() {
		return this.httpsPortMappings;
	}

	public Integer lookupHttpPort(Integer httpsPort) {
		for (Integer httpPort : this.httpsPortMappings.keySet()) {
			if (this.httpsPortMappings.get(httpPort).equals(httpsPort)) {
				return httpPort;
			}
		}

		return null;
	}

	public Integer lookupHttpsPort(Integer httpPort) {
		return this.httpsPortMappings.get(httpPort);
	}

	/**
	 * Set to override the default HTTP port to HTTPS port mappings of 80:443, and
	 * 8080:8443. In a Spring XML ApplicationContext, a definition would look something
	 * like this:
	 *
	 * <pre>
	 *  &lt;property name="portMappings"&gt;
	 *      &lt;map&gt;
	 *          &lt;entry key="80"&gt;&lt;value&gt;443&lt;/value&gt;&lt;/entry&gt;
	 *          &lt;entry key="8080"&gt;&lt;value&gt;8443&lt;/value&gt;&lt;/entry&gt;
	 *      &lt;/map&gt;
	 * &lt;/property&gt;
	 * </pre>
	 *
	 * @param newMappings A Map consisting of String keys and String values, where for
	 * each entry the key is the string representation of an integer HTTP port number, and
	 * the value is the string representation of the corresponding integer HTTPS port
	 * number.
	 *
	 * @throws IllegalArgumentException if input map does not consist of String keys and
	 * values, each representing an integer port number in the range 1-65535 for that
	 * mapping.
	 */
	public void setPortMappings(Map<String, String> newMappings) {
		Assert.notNull(newMappings,
				"A valid list of HTTPS port mappings must be provided");

		this.httpsPortMappings.clear();

		for (Map.Entry<String, String> entry : newMappings.entrySet()) {
			Integer httpPort = Integer.valueOf(entry.getKey());
			Integer httpsPort = Integer.valueOf(entry.getValue());

			if ((httpPort.intValue() < 1) || (httpPort.intValue() > 65535)
					|| (httpsPort.intValue() < 1) || (httpsPort.intValue() > 65535)) {
				throw new IllegalArgumentException(
						"one or both ports out of legal range: " + httpPort + ", "
								+ httpsPort);
			}

			this.httpsPortMappings.put(httpPort, httpsPort);
		}

		if (this.httpsPortMappings.size() < 1) {
			throw new IllegalArgumentException("must map at least one port");
		}
	}
}
