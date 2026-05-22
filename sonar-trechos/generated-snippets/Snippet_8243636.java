public class Snippet__8243636 {

    public Snippet__8243636(ParsingContext parsingContext, StartElement raw, Attributes attrs, Stack stack)
                throws SAXException {
            super(raw);
            this.parameters = new HashMap();
            Locator locator = getLocation();

            String name = attrs.getValue("macro");
            if (name == null) {
                throw new SAXParseException("if: \"test\" is required", locator,
                        null);
            }
            this.macro = parsingContext.getStringTemplateParser().compileExpr(name, "call: \"macro\": ",
                    locator);

            String namespace = StringUtils.defaultString(attrs
                    .getValue("targetNamespace"));
            this.targetNamespace = parsingContext.getStringTemplateParser().compileExpr(namespace,
                    "call: \"targetNamespace\": ", locator);
        }

}
