public abstract class AbstractActiveMQProducerResource extends AbstractActiveMQClientResource {
    MessageProducer producer;

    public AbstractActiveMQProducerResource(ActiveMQConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public AbstractActiveMQProducerResource(URI brokerURI) {
        super(brokerURI);
    }

    public AbstractActiveMQProducerResource(EmbeddedActiveMQBroker embeddedActiveMQBroker) {
        super(embeddedActiveMQBroker);
    }

    public AbstractActiveMQProducerResource(URI brokerURI, String userName, String password) {
        super(brokerURI, userName, password);
    }

    public AbstractActiveMQProducerResource(String destinationName, ActiveMQConnectionFactory connectionFactory) {
        super(destinationName, connectionFactory);
    }

    public AbstractActiveMQProducerResource(String destinationName, URI brokerURI) {
        super(destinationName, brokerURI);
    }

    public AbstractActiveMQProducerResource(String destinationName, EmbeddedActiveMQBroker embeddedActiveMQBroker) {
        super(destinationName, embeddedActiveMQBroker);
    }

    public AbstractActiveMQProducerResource(String destinationName, URI brokerURI, String userName, String password) {
        super(destinationName, brokerURI, userName, password);
    }

    @Override
    public String getDestinationName() {
        try {
            if (producer != null && producer.getDestination() != null) {
                return producer.getDestination().toString();
            }
        } catch (JMSException e) {
            // eat this
        }

        return null;
    }

    public void sendMessage(Message message) throws JMSException {
        producer.send(message);
    }

    public BytesMessage sendMessage(byte[] body) throws JMSException {
        BytesMessage message = this.createMessage(body);
        sendMessage(message);
        return message;
    }

    public TextMessage sendMessage(String body) throws JMSException {
        TextMessage message = this.createMessage(body);
        sendMessage(message);
        return message;
    }

    public MapMessage sendMessage(Map<String, Object> body) throws JMSException {
        MapMessage message = this.createMessage(body);
        sendMessage(message);
        return message;
    }

    public ObjectMessage sendMessage(Serializable body) throws JMSException {
        ObjectMessage message = this.createMessage(body);
        sendMessage(message);
        return message;
    }

    public BytesMessage sendMessageWithProperties(byte[] body, Map<String, Object> properties) throws JMSException {
        BytesMessage message = this.createMessage(body, properties);
        sendMessage(message);
        return message;
    }

    public TextMessage sendMessageWithProperties(String body, Map<String, Object> properties) throws JMSException {
        TextMessage message = this.createMessage(body, properties);
        sendMessage(message);
        return message;
    }

    public MapMessage sendMessageWithProperties(Map<String, Object> body, Map<String, Object> properties) throws JMSException {
        MapMessage message = this.createMessage(body, properties);
        sendMessage(message);
        return message;
    }

    public ObjectMessage sendMessageWithProperties(Serializable body, Map<String, Object> properties) throws JMSException {
        ObjectMessage message = this.createMessage(body, properties);
        sendMessage(message);
        return message;
    }

}
