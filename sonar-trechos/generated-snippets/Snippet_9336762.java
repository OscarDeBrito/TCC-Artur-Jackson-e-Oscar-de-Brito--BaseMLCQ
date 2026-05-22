public class Snippet__9336762 {

    protected void doClick(final MenuSelectionManager msm) {
            final Dimension size = menuItem.getSize();
            AquaUtils.blinkMenu(new AquaUtils.Selectable() {
                public void paintSelected(final boolean selected) {
                    menuItem.setArmed(selected);
                    menuItem.paintImmediately(0, 0, size.width, size.height);
                }
            });
            super.doClick(msm);
        }

}
