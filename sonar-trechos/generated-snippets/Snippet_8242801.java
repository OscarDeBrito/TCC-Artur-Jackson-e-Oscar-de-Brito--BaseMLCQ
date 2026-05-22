public class Snippet__8242801 {

    public void startElement(String uri,
                                 String name,
                                 String raw,
                                 Attributes attr)
        throws SAXException {
            if (namespaceURI.equals(uri) && ignoreHooksCount == 0) {
                // this is our namespace:
                try {
                    startTransformingElement(uri, name, raw, attr);
                } catch (ProcessingException e) {
                    throw new SAXException("ProcessingException: " + e, e);
                } catch (IOException e) {
                    throw new SAXException("IOException occured during processing: " + e, e);
                }
            } else {
                if (ignoreEventsCount == 0) {
                    super.startElement(uri, name, raw, attr);
                }
            }
        }

}
