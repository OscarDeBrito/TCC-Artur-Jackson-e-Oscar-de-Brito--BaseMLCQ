public class Snippet__9209481 {

    public Snippet__9209481(int size) throws SAXException {
            this(size, null);
            try {
                _dtmManager = XSLTCDTMManager.createNewDTMManagerInstance();
            } catch (Exception e) {
                throw new SAXException(e);
            }
        }

}
