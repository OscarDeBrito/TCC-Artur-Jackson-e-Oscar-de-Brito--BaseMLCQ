public class SendFileRecordsToQueueBean {

    private static final Logger LOG = LoggerFactory.getLogger(SendFileRecordsToQueueBean.class);

    @Produce("activemq:personnel.records")
    ProducerTemplate producer;

    @Consume("file:src/data?noop=true")
    public void onFileSendToQueue(String body, @Header("CamelFileName") String name) {
        LOG.info("Incoming file: {}", name);
        producer.sendBody(body);
    }
}
