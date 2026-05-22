public class GenerationHelperContentHandler extends DelegatingContentHandler {

    private static final Attributes EMPTY_ATTS = new AttributesImpl();

    private String mainNamespace;
    private Object contentHandlerContext;

    /**
     * Main constructor. If the given handler also implements any of the EntityResolver,
     * DTDHandler, LexicalHandler or ErrorHandler interfaces, these are set automatically.
     * @param handler the SAX content handler to delegate all calls to
     * @param mainNamespace the main namespace used for generated XML content when abbreviated
     *                          ContentHandler calls are used.
     * @param contentHandlerContext additional content handler context state
     */
    public GenerationHelperContentHandler(ContentHandler handler, String mainNamespace, Object contentHandlerContext) {
        super(handler);
        this.mainNamespace = mainNamespace;
        this.contentHandlerContext = contentHandlerContext;
    }

    /**
     * Returns the main namespace used for generated XML content.
     * @return the main namespace
     */
    public String getMainNamespace() {
        return this.mainNamespace;
    }

    /**
     * Sets the main namespace used for generated XML content when abbreviated ContentHandler
     * calls are used.
     * @param namespaceURI the new main namespace URI
     */
    public void setMainNamespace(String namespaceURI) {
        this.mainNamespace = namespaceURI;
    }

    /**
     * Returns the context object (may be null).
     * @return the context object
     */
    public Object getContentHandlerContext() {
        return this.contentHandlerContext;
    }

    /**
     * Convenience method to generate a startElement SAX event.
     * @param localName the local name of the element
     * @param atts the attributes
     * @throws SAXException if a SAX exception occurs
     */
    public void startElement(String localName, Attributes atts) throws SAXException {
        getDelegateContentHandler().startElement(getMainNamespace(), localName, localName, atts);
    }

    /**
     * Convenience method to generate a startElement SAX event.
     * @param localName the local name of the element
     * @throws SAXException if a SAX exception occurs
     */
    public void startElement(String localName) throws SAXException {
        startElement(localName, EMPTY_ATTS);
    }

    /**
     * Convenience method to generate a startElement SAX event.
     * @param qName the qualified name of the element
     * @param atts the attributes
     * @throws SAXException if a SAX exception occurs
     */
    public void startElement(QName qName, Attributes atts) throws SAXException {
        getDelegateContentHandler().startElement(qName.getNamespaceURI(), qName.getLocalName(),
                qName.getQName(), atts);
    }

    /**
     * Convenience method to generate a startElement SAX event.
     * @param qName the qualified name of the element
     * @throws SAXException if a SAX exception occurs
     */
    public void startElement(QName qName) throws SAXException {
        startElement(qName, EMPTY_ATTS);
    }

    /**
     * Convenience method to generate a endElement SAX event.
     * @param localName the local name of the element
     * @throws SAXException if a SAX exception occurs
     */
    public void endElement(String localName) throws SAXException {
        getDelegateContentHandler().endElement(getMainNamespace(), localName, localName);
    }

    /**
     * Convenience method to generate a startElement SAX event.
     * @param qName the qualified name of the element
     * @throws SAXException if a SAX exception occurs
     */
    public void endElement(QName qName) throws SAXException {
        getDelegateContentHandler().endElement(qName.getNamespaceURI(), qName.getLocalName(),
                qName.getQName());
    }

    /**
     * Convenience method to generate an empty element with attributes.
     * @param localName the local name of the element
     * @param atts the attributes
     * @throws SAXException if a SAX exception occurs
     */
    public void element(String localName, Attributes atts) throws SAXException {
        getDelegateContentHandler().startElement(getMainNamespace(), localName, localName, atts);
        getDelegateContentHandler().endElement(getMainNamespace(), localName, localName);
    }

    /**
     * Convenience method to generate an empty element with attributes.
     * @param qName the qualified name of the element
     * @param atts the attributes
     * @throws SAXException if a SAX exception occurs
     */
    public void element(QName qName, Attributes atts) throws SAXException {
        getDelegateContentHandler().startElement(qName.getNamespaceURI(), qName.getLocalName(),
                qName.getQName(), atts);
        getDelegateContentHandler().endElement(qName.getNamespaceURI(), qName.getLocalName(),
                qName.getQName());
    }

}
