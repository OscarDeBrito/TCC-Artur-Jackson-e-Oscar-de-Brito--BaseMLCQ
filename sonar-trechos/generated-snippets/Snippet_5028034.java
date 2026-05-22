public interface EventBatchListener extends EventListener {
    /**
     * Returns the string representing the identifier
     * that this instance is only interested in, or
     * null if this instance is interested in ALL events from
     * any identifier
     *
     * @return String identifier of the interested source
     */
    String getInterestedIdentifier();

    /**
     * Implementations receive a list of LoggingEvent instances only if they are interested,
     * that is, if the source of the eventBatch matches this instances interested identifier
     *
     * @param events     List of LoggingEvent instances
     * @param identifier the identifier this list of LoggingEvents is associated with
     */
    void receiveEventBatch(String identifier, List<org.apache.log4j.spi.LoggingEvent> events);
}
