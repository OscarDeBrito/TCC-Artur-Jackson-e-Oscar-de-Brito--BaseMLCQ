protected class MessageConsumerResourcesFactory extends BasePoolableObjectFactory<MessageConsumerResources> {

        @Override
        public MessageConsumerResources makeObject() throws Exception {
            MessageConsumerResources answer;
            ConnectionResource connectionResource = getOrCreateConnectionResource();
            Connection conn = connectionResource.borrowConnection();
            try {
                Session session;
                if (isEndpointTransacted()) {
                    session = conn.createSession(true, Session.SESSION_TRANSACTED);
                } else {
                    session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
                }

                Destination replyToDestination;
                boolean isReplyToTopic = false;
                if (ObjectHelper.isEmpty(getNamedReplyTo())) {
                    isReplyToTopic = isTopic();
                    replyToDestination = getEndpoint().getDestinationCreationStrategy().createTemporaryDestination(session, isReplyToTopic);
                } else {
                    DestinationNameParser parser = new DestinationNameParser();
                    isReplyToTopic = parser.isNamedReplyToTopic(getNamedReplyTo(), isTopic());
                    replyToDestination = getEndpoint().getDestinationCreationStrategy().createDestination(session, getNamedReplyTo(), isReplyToTopic);
                }
                MessageConsumer messageConsumer = getEndpoint().getJmsObjectFactory().createMessageConsumer(session, replyToDestination, null, isReplyToTopic, null, true, false, false);
                messageConsumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(final Message message) {
                        log.debug("Message Received in the Consumer Pool");
                        log.debug("  Message : {}", message);
                        try {
                            Exchanger<Object> exchanger = EXCHANGERS.get(message.getJMSCorrelationID());
                            exchanger.exchange(message, getResponseTimeOut(), TimeUnit.MILLISECONDS);
                        } catch (Exception e) {
                            log.warn("Unable to exchange message: {}. This exception is ignored.", message, e);
                        }
                    }
                });
                answer = new MessageConsumerResources(session, messageConsumer, replyToDestination);
            } catch (Exception e) {
                log.error("Unable to create the MessageConsumerResource: {}", e.getLocalizedMessage());
                throw new CamelException(e);
            } finally {
                connectionResource.returnConnection(conn);
            }
            return answer;
        }

        @Override
        public void destroyObject(MessageConsumerResources model) throws Exception {
            if (model.getMessageConsumer() != null) {
                model.getMessageConsumer().close();
            }

            if (model.getSession() != null) {
                if (model.getSession().getTransacted()) {
                    try {
                        model.getSession().rollback();
                    } catch (Exception e) {
                        // Do nothing. Just make sure we are cleaned up
                    }
                }
                model.getSession().close();
            }
        }
    }
