@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(org.eclipse.jetty.reactive.client.ReactiveRequest.class)
	@ConditionalOnMissingBean(ClientHttpConnector.class)
	public static class JettyClient {

		@Bean
		@ConditionalOnMissingBean
		public JettyResourceFactory jettyClientResourceFactory() {
			return new JettyResourceFactory();
		}

		@Bean
		public JettyClientHttpConnector jettyClientHttpConnector(
				JettyResourceFactory jettyResourceFactory) {
			return new JettyClientHttpConnector(jettyResourceFactory, (httpClient) -> {
			});
		}

	}
