public final class LegendFont extends Font {

    public static final Color FOREGROUND_COLOR = new Color(100, 100, 100);
    public static final Color BACKGROUND_COLOR = new Color(255, 255, 255);

    private static final Font baseFont = baseFont();


    public LegendFont() {
        super(baseFont);
    }


    private static Font baseFont() {
        Font font = new JLabel().getFont();
        return new Font(font.getName(), font.getStyle(), font.getSize() - 2);
    }

}
