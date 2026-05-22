public class SchematronProcessor {

    private Logger logger = LoggerFactory.getLogger(SchematronProcessor.class);
    private XMLReader reader;
    private Templates templates;

    /**
     * Constructor setting the XSLT schematron templates.
     *
     * @param reader
     * @param templates
     */
    public SchematronProcessor(XMLReader reader, Templates templates) {
        this.reader = reader;
        this.templates = templates;
    }

    /**
     * Validates the given XML for given Rules.
     *
     * @param xml
     * @return
     */
    public String validate(final String xml) {
        final Source source = new SAXSource(reader, new InputSource(IOUtils.toInputStream(xml)));
        return validate(source);
    }

    /**
     * Validates the given XML for given Rules.
     *
     * @param source
     * @return
     */
    public String validate(Source source) {
        try {
            final StringWriter writer = new StringWriter();
            templates.newTransformer().transform(source, new StreamResult(writer));
            return writer.toString();
        } catch (TransformerException e) {
            logger.error(e.getMessage());
            throw new SchematronValidationException("Failed to apply Schematron validation transform", e);
        }
    }
}
