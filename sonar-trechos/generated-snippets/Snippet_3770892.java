public class Snippet__3770892 {

    private static void printCodePointAsFourHexDigits(Appendable out, int c)
            throws IOException
        {
            String s = Integer.toHexString(c);
            out.append("\\u");
            out.append(ZERO_PADDING[4-s.length()]);
            out.append(s);
        }

}
