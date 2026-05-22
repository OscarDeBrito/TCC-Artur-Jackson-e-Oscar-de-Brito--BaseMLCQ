public class ObjectStringMessageConverter extends StringMessageConverter {

	@Override
	protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
		Object payload = message.getPayload();
		if (payload instanceof String || payload instanceof byte[]) {
			return super.convertFromInternal(message, targetClass, conversionHint);
		}
		else {
			return payload.toString();
		}
	}

}
