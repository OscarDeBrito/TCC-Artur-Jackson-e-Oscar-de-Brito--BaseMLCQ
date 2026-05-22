@SuppressWarnings("serial")
public class FillStroke extends Surface {

    public FillStroke() {
        setBackground(WHITE);
    }

    @Override
    public void render(int w, int h, Graphics2D g2) {
        GeneralPath p = new GeneralPath(Path2D.WIND_EVEN_ODD);
        p.moveTo(w * .5f, h * .15f);
        p.lineTo(w * .8f, h * .75f);
        p.lineTo(w * .2f, h * .75f);
        g2.setColor(LIGHT_GRAY);
        g2.fill(p);
        g2.setColor(BLACK);
        g2.setStroke(new BasicStroke(10));
        g2.draw(p);
        TextLayout tl = new TextLayout("Fill, Stroke w/o closePath",
                g2.getFont(), g2.getFontRenderContext());
        tl.draw(g2, (float) (w / 2 - tl.getBounds().getWidth() / 2), h * .85f);
    }

    public static void main(String[] s) {
        createDemoFrame(new FillStroke());
    }
}
