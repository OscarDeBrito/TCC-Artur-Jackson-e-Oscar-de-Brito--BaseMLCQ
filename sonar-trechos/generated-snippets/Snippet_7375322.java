public class Snippet__7375322 {

    @Bean
    		@ConditionalOnSingleCandidate(RabbitTemplate.class)
    		public RabbitMessagingTemplate rabbitMessagingTemplate(
    				RabbitTemplate rabbitTemplate) {
    			return new RabbitMessagingTemplate(rabbitTemplate);
    		}

}
