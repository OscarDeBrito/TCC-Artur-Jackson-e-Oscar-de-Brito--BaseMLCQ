private class DocumentHeaderHandler extends AbstractElementHandler {

            public void startElement(Attributes attributes) throws IFException {
                documentHandler.startDocumentHeader();
            }

            public void endElement() throws IFException {
                documentHandler.endDocumentHeader();
            }

        }
