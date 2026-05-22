public class StringMessage implements XMLizable {
    private char[] ch;

    public StringMessage(String message) {
        this.ch = message.toCharArray();
    }

    public void toSAX(ContentHandler contentHandler) throws SAXException {
        contentHandler.characters(ch, 0, ch.length);
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof StringMessage) {
            // Compare char arrays
            return Arrays.equals(this.ch, ((StringMessage)obj).ch);
        } else {
            return false;
        }
    }
}
