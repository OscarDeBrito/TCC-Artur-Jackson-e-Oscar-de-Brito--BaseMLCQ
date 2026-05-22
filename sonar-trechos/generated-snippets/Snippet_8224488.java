protected class StylingContentHandler extends AbstractXMLPipe {

        private int elementNesting;
        private SaxBuffer styling;

        public void setSaxFragment(SaxBuffer saxFragment) {
            styling = saxFragment;
        }

        public void recycle() {
            super.recycle();
            elementNesting = 0;
            styling = null;
        }

        public void startElement(String uri, String loc, String raw, Attributes a)
        throws SAXException {
            elementNesting++;
            super.startElement(uri, loc, raw, a);
        }

        public void endElement(String uri, String loc, String raw)
        throws SAXException {
            elementNesting--;
            if (elementNesting == 0) {
                styling.toSAX(getContentHandler());
            }
            super.endElement(uri, loc, raw);
        }
    }
