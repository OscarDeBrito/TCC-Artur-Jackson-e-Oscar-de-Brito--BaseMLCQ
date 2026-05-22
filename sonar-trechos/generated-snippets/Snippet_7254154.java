private static class CoreDumpAdder extends SwingWorker {
        volatile private ProgressHandle ph = null;
        volatile private boolean success = false;
        private CoreDumpImpl newCoreDump;
        private Storage storage;
        private String[] propNames, propValues;
        
        public CoreDumpAdder(CoreDumpImpl newCoreDump, Storage storage, String[] propNames, String[] propValues) {
            this.newCoreDump = newCoreDump;
            this.storage = storage;
            this.propValues = propValues;
            this.propNames = propNames;
        }
        
        @Override
        protected void doInBackground() {
            SaModel model = SaModelFactory.getSAAgentFor(newCoreDump);
            if (model != null) {
                storage.setCustomProperties(propNames, propValues);
                CoreDumpsContainer.sharedInstance().getRepository().addDataSource(newCoreDump);

                success = true;
            }
        }

        @Override
        protected void nonResponding() {
            ph = ProgressHandleFactory.createHandle(NbBundle.getMessage(CoreDumpProvider.class, "LBL_Inspecting_core_dump"));   // NOI18N
            ph.start();
        }

        @Override
        protected void done() {
            if (ph != null) {
                ph.finish();
            }
            if (!success) {
                DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(CoreDumpProvider.class, "MSG_not_valid_core_dump", newCoreDump.getFile().getAbsolutePath())));  // NOI18N
            }
        }
    }
