private class ExplorerTreeSelectionListener implements TreeSelectionListener {

        public void valueChanged(TreeSelectionEvent e) {
            Set<DataSource> selectedDataSources = getSelectedDataSources();
            Set<ExplorerSelectionListener> listeners = new HashSet(selectionListeners);
            for (ExplorerSelectionListener listener : listeners) listener.selectionChanged(selectedDataSources);
        }
        
    }
