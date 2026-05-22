public class Snippet__9443834 {

    @SuppressWarnings("serial") // Superclass is not serializable across versions
        private class SystemMenuBar extends JMenuBar {
            public void paint(Graphics g) {
                if (isOpaque()) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, getWidth(), getHeight());
                }

                if (systemIcon != null) {
                    g.drawImage(systemIcon, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
                } else {
                    Icon icon = UIManager.getIcon("InternalFrame.icon");

                    if (icon != null) {
                        icon.paintIcon(this, g, 0, 0);
                    }
                }
            }
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();

                return new Dimension(Math.max(IMAGE_WIDTH, size.width),
                                     Math.max(size.height, IMAGE_HEIGHT));
            }
        }

}
