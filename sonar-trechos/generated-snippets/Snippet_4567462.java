private class DocumentTrailerHandler extends AbstractElementHandler {

            public void startElement(Attributes attributes) throws IFException {
                documentHandler.startDocumentTrailer();
            }

            public void endElement() throws IFException {
                documentHandler.endDocumentTrailer();
            }

        }
