public class Snippet__4735672 {

    private static void validateArguments(final int p, final int sp) throws UDFArgumentException {
            if (p < 4 || p > sp) {
                throw new UDFArgumentException("p must be between 4 and sp (inclusive)");
            }
            if (sp > 32) {
                throw new UDFArgumentException("sp values greater than 32 not supported");
            }
        }

}
