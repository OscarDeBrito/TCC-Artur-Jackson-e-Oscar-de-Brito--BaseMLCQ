private class DelayedSelectionUpdater implements Runnable {
        DelayedSelectionUpdater() {
            SwingUtilities.invokeLater(this);
        }

        public void run() {
            updateFileNameCompletion();
        }
    }
