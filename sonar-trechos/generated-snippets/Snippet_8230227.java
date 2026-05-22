public class Snippet__8230227 {

    public void ignorableWhitespace(char ch[], int start, int len)
        throws SAXException {
            this.log ("ignorableWhitespace", new String(ch,start,len));
            if (super.contentHandler!=null) {
                super.contentHandler.ignorableWhitespace(ch,start,len);
            }
        }

}
