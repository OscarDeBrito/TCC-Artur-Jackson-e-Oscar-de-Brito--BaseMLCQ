class AxiomSoap11Fault extends AxiomSoapFault implements Soap11Fault {

	AxiomSoap11Fault(SOAPFault axiomFault, SOAPFactory axiomFactory) {
		super(axiomFault, axiomFactory);
	}

	@Override
	public QName getFaultCode() {
		return getAxiomFault().getCode().getTextAsQName();
	}

	@Override
	public String getFaultStringOrReason() {
		if (getAxiomFault().getReason() != null) {
			return getAxiomFault().getReason().getText();
		}
		return null;
	}

	@Override
	public Locale getFaultStringLocale() {
		if (getAxiomFault().getReason() != null) {
			OMAttribute langAttribute =
					getAxiomFault().getReason().getAttribute(new QName("http://www.w3.org/XML/1998/namespace", "lang"));
			if (langAttribute != null) {
				String xmlLangString = langAttribute.getAttributeValue();
				if (xmlLangString != null) {
					return AxiomUtils.toLocale(xmlLangString);
				}

			}
		}
		return null;
	}

}
