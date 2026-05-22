public class Snippet__9410474 {

    public void comment(char[] chars, int offset, int length)
            throws SAXException
        {
            if (_dom == null) {
               prepareNewDOM();
            }

            maybeEmitStartElement();
            _dom.comment(chars, offset, length);
        }

}
