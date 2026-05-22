private class UniversalCellTipListener implements ComponentListener, KeyListener, FocusListener, PropertyChangeListener,
                                                      HierarchyListener, HierarchyBoundsListener {
        //~ Methods --------------------------------------------------------------------------------------------------------------

        public void ancestorMoved(HierarchyEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void ancestorResized(HierarchyEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void componentHidden(ComponentEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void componentMoved(ComponentEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void componentResized(ComponentEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void componentShown(ComponentEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void focusGained(FocusEvent e) {
            //
        }

        public void focusLost(FocusEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void hierarchyChanged(HierarchyEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        public void keyPressed(KeyEvent e) {
            hideCellTipAlways();
        }

        public void keyReleased(KeyEvent e) {
            //
        }

        public void keyTyped(KeyEvent e) {
            //
        }

        public void propertyChange(PropertyChangeEvent e) {
            hideCellTipForOwner(e.getSource());
        }

        void registerForComponent(JComponent component) {
            if (component == null) {
                return;
            }

            component.addComponentListener(this);
            component.addKeyListener(this);
            component.addFocusListener(this);
            component.addPropertyChangeListener(this);
            component.addHierarchyListener(this);
            component.addHierarchyBoundsListener(this);
        }

        void unregisterForComponent(JComponent component) {
            if (component == null) {
                return;
            }

            component.removeComponentListener(this);
            component.removeKeyListener(this);
            component.removeFocusListener(this);
            component.removePropertyChangeListener(this);
            component.removeHierarchyListener(this);
            component.removeHierarchyBoundsListener(this);
        }

        private void hideCellTipAlways() {
            hideCellTip();
        }

        private void hideCellTipForOwner(Object owner) {
            if (cellTipComponent == owner) {
                hideCellTip();
            }
        }
    }
