private class FirstPaintListener implements PaintListener {
        @Override
        public void paintControl(final PaintEvent e) {
            ((Composite) e.widget).removePaintListener(this);
            hookDialogIsOpen();
        }
    }
