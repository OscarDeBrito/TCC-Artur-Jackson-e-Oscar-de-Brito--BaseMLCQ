public final class DirectRabbitListenerContainerFactoryConfigurer extends
		AbstractRabbitListenerContainerFactoryConfigurer<DirectRabbitListenerContainerFactory> {

	@Override
	public void configure(DirectRabbitListenerContainerFactory factory,
			ConnectionFactory connectionFactory) {
		PropertyMapper map = PropertyMapper.get();
		RabbitProperties.DirectContainer config = getRabbitProperties().getListener()
				.getDirect();
		configure(factory, connectionFactory, config);
		map.from(config::getConsumersPerQueue).whenNonNull()
				.to(factory::setConsumersPerQueue);
	}

}
