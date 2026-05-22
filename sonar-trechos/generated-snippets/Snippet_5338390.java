public class DocumentReaderCallback implements InputStreamCallback {

    private final boolean isNamespaceAware;
    private Document document;

    /**
     * Creates a new DocumentReaderCallback .
     *
     * @param isNamespaceAware Whether or not the parse should consider namespaces
     */
    public DocumentReaderCallback(boolean isNamespaceAware) {
        this.isNamespaceAware = isNamespaceAware;
    }

    @Override
    public void process(final InputStream stream) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(isNamespaceAware);
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(stream);
        } catch (ParserConfigurationException pce) {
            throw new IOException(pce.getLocalizedMessage(), pce);
        } catch (SAXException saxe) {
            throw new IOException(saxe.getLocalizedMessage(), saxe);
        }
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }
}
