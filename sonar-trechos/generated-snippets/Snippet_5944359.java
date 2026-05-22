public class ContainerElement extends ServiceElement<Container> {

        public ContainerElement() {
            super(new Container());
        }

        @Override
        public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            final String ctype = attributes.getValue("ctype");
            if (ctype != null) {
                service.setType(ctype);
            }
        }

        @Override
        public void endElement(final String uri, final String localName, final String qName) {
            openejb.getContainer().add(service);
            super.endElement(uri, localName, qName);
        }

        @Override
        protected List<String> getAttributes() {
            final List<String> attributes = new ArrayList<>(super.getAttributes());
            attributes.add("ctype");
            return attributes;
        }
    }
