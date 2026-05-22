public class RMDeliveryInterceptor extends AbstractRMInterceptor<Message> {

    private static final Logger LOG = LogUtils.getL7dLogger(RMDeliveryInterceptor.class);

    public RMDeliveryInterceptor() {
        super(Phase.POST_INVOKE);
        addBefore(OutgoingChainInterceptor.class.getName());
    }

    // Interceptor interface

    public void handle(Message message) throws SequenceFault, RMException {
        final AddressingProperties maps = ContextUtils.retrieveMAPs(message, false, false, false);
        //if wsrmp:RMAssertion and addressing is optional
        if (maps == null && isRMPolicyEnabled(message)) {
            return;
        }
        LOG.entering(getClass().getName(), "handleMessage");
        Destination dest = getManager().getDestination(message);
        final boolean robust =
            MessageUtils.getContextualBoolean(message, Message.ROBUST_ONEWAY, false);
        if (robust) {
            message.remove(RMMessageConstants.DELIVERING_ROBUST_ONEWAY);
            dest.acknowledge(message);
        }
        dest.processingComplete(message);

        // close InputStream of RMCaptureInInterceptor, to delete tmp files in filesystem
        Closeable closable = (Closeable)message.get("org.apache.cxf.ws.rm.content.closeable");
        if (null != closable) {
            try {
                closable.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }
}
