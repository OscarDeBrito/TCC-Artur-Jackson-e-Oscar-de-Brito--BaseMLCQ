public class KuraUnmatchedMessage extends KuraMessage<KuraUnmatchedChannel, KuraUnmatchedPayload> implements DeviceMessage<KuraUnmatchedChannel, KuraUnmatchedPayload> {

    /**
     * Constructor
     */
    public KuraUnmatchedMessage() {
        super();
    }

    /**
     * Constructor
     *
     * @param channel
     * @param timestamp
     * @param payload
     */
    public KuraUnmatchedMessage(KuraUnmatchedChannel channel, Date timestamp, KuraUnmatchedPayload payload) {
        super(channel, timestamp, payload);
    }

}
