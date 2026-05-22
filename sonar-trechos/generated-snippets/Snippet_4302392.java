public class Snippet__4302392 {

    @Override
        public String toString() {
            String typeStr = tag().typeStr() + " ["
                + "tag=" + tag()
                + ", len=" + getHeaderLength() + "+" + getBodyLength()
                + "] ";
            return typeStr + "eoc";
        }

}
