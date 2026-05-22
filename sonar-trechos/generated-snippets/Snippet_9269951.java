public class Snippet__9269951 {

    @BeanProperty(bound = false)
        public boolean getScrollableTracksViewportWidth() {
            Container parent = SwingUtilities.getUnwrappedParent(this);
            if (parent instanceof JViewport) {
                JViewport port = (JViewport) parent;
                TextUI ui = getUI();
                int w = port.getWidth();
                Dimension min = ui.getMinimumSize(this);
                Dimension max = ui.getMaximumSize(this);
                if ((w >= min.width) && (w <= max.width)) {
                    return true;
                }
            }
            return false;
        }

}
