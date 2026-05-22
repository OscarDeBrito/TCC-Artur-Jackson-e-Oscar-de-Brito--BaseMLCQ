public class SoapEnvelopeLoggingInterceptor extends AbstractLoggingInterceptor implements SoapEndpointInterceptor {

	private boolean logFault = true;

	/** Indicates whether a SOAP Fault should be logged. Default is {@code true}. */
	public void setLogFault(boolean logFault) {
		this.logFault = logFault;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		if (logFault && isLogEnabled()) {
			logMessageSource("Fault: ", getSource(messageContext.getResponse()));
		}
		return true;
	}

	@Override
	public boolean understands(SoapHeaderElement header) {
		return false;
	}

	@Override
	protected Source getSource(WebServiceMessage message) {
		if (message instanceof SoapMessage) {
			SoapMessage soapMessage = (SoapMessage) message;
			return soapMessage.getEnvelope().getSource();
		}
		else {
			return null;
		}
	}
}
