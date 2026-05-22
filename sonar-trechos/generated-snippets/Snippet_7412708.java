class GatewayReceiverParser extends AbstractSimpleBeanDefinitionParser {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?> getBeanClass(Element element) {
		return GatewayReceiverFactoryBean.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		String cacheRef = element.getAttribute(ParsingUtils.CACHE_REF_ATTRIBUTE_NAME);

		builder.addConstructorArgReference(SpringUtils.defaultIfEmpty(
			cacheRef, GemfireConstants.DEFAULT_GEMFIRE_CACHE_NAME));

		builder.setLazyInit(false);

		ParsingUtils.setPropertyValue(element, builder, "bind-address");
		ParsingUtils.setPropertyValue(element, builder, "hostname-for-senders");
		ParsingUtils.setPropertyValue(element, builder, "start-port");
		ParsingUtils.setPropertyValue(element, builder, "end-port");
		ParsingUtils.setPropertyValue(element, builder, "manual-start");
		ParsingUtils.setPropertyValue(element, builder, "maximum-time-between-pings");
		ParsingUtils.setPropertyValue(element, builder, "socket-buffer-size");
		ParsingUtils.parseTransportFilters(element, parserContext, builder);
	}
}
