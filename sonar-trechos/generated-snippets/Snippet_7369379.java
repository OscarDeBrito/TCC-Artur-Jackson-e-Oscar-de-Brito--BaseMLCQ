@Configuration(proxyBeanMethods = false)
@ConditionalOnEnabledEndpoint(endpoint = HttpTraceEndpoint.class)
@ConditionalOnExposedEndpoint(endpoint = HttpTraceEndpoint.class)
@AutoConfigureAfter(HttpTraceAutoConfiguration.class)
public class HttpTraceEndpointAutoConfiguration {

	@Bean
	@ConditionalOnBean(HttpTraceRepository.class)
	@ConditionalOnMissingBean
	public HttpTraceEndpoint httpTraceEndpoint(HttpTraceRepository traceRepository) {
		return new HttpTraceEndpoint(traceRepository);
	}

}
