public class Snippet__9219509 {

    static JComponent getPaintingOrigin(JComponent c) {
            Container p = c;
            while ((p = p.getParent()) instanceof JComponent) {
                JComponent jp = (JComponent) p;
                if (jp.isPaintingOrigin()) {
                    return jp;
                }
            }
            return null;
        }

}
