@SuppressWarnings("restriction")
public class NotificationListeningChannelAdapterModelElement extends AbstractInboundChannelAdapterModelElement {

	public NotificationListeningChannelAdapterModelElement() {
		super();
	}

	public NotificationListeningChannelAdapterModelElement(IDOMElement input, AbstractConfigGraphDiagram diagram) {
		super(input, diagram);
	}

	@Override
	public String getInputName() {
		return IntJmxSchemaConstants.ELEM_NOTIFICATION_LISTENING_CHANNEL_ADAPTER;
	}

}
