public class Snippet__9371099 {

    public Dimension getMinimumSize(int rows) {
            FontMetrics fm = getFontMetrics(getFont());
            initFontMetrics();
            return new Dimension(20 + fm.stringWidth("0123456789abcde"),
                                 getItemHeight() * rows + (2*MARGIN));
        }

}
