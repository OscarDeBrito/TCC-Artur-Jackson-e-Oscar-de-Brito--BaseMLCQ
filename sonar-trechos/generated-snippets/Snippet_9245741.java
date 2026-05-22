public class Snippet__9245741 {

    @Override
            protected void uninstallListeners(JComponent scrollPane) {
                super.uninstallListeners(scrollPane);
                scrollPane.removePropertyChangeListener(propertyChangeHandler);
            }

}
