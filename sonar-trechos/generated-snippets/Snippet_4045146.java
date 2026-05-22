public class LSMTwoPCBTreeWithBuddyBulkLoader implements IIndexBulkLoader, ITwoPCIndexBulkLoader {
        private final ILSMDiskComponent component;
        private final LoadOperation loadOp;
        private final ILSMDiskComponentBulkLoader componentBulkLoader;
        private final boolean isTransaction;

        public LSMTwoPCBTreeWithBuddyBulkLoader(float fillFactor, boolean verifyInput, long numElementsHint,
                boolean isTransaction, Map<String, Object> parameters) throws HyracksDataException {
            this.isTransaction = isTransaction;
            // Create the appropriate target
            LSMComponentFileReferences componentFileRefs;
            if (isTransaction) {
                try {
                    componentFileRefs = fileManager.getNewTransactionFileReference();
                } catch (IOException e) {
                    throw HyracksDataException.create(e);
                }
                component =
                        createDiskComponent(bulkLoadComponentFactory, componentFileRefs.getInsertIndexFileReference(),
                                componentFileRefs.getDeleteIndexFileReference(),
                                componentFileRefs.getBloomFilterFileReference(), true);
            } else {
                componentFileRefs = fileManager.getRelFlushFileReference();
                component =
                        createDiskComponent(bulkLoadComponentFactory, componentFileRefs.getInsertIndexFileReference(),
                                componentFileRefs.getDeleteIndexFileReference(),
                                componentFileRefs.getBloomFilterFileReference(), true);
            }

            loadOp = new LoadOperation(componentFileRefs, ioOpCallback, getIndexIdentifier(), parameters);
            loadOp.setNewComponent(component);
            ioOpCallback.scheduled(loadOp);
            ioOpCallback.beforeOperation(loadOp);
            componentBulkLoader =
                    component.createBulkLoader(loadOp, fillFactor, verifyInput, numElementsHint, false, true, false);
        }

        @Override
        public void add(ITupleReference tuple) throws HyracksDataException {
            componentBulkLoader.add(tuple);
        }

        @Override
        public void end() throws HyracksDataException {
            try {
                ioOpCallback.afterOperation(loadOp);
                componentBulkLoader.end();
                if (component.getComponentSize() > 0) {
                    if (isTransaction) {
                        // Since this is a transaction component, validate and
                        // deactivate. it could later be added or deleted
                        try {
                            component.markAsValid(durable, loadOp);
                        } finally {
                            ioOpCallback.afterFinalize(loadOp);
                        }
                        component.deactivate();
                    } else {
                        ioOpCallback.afterFinalize(loadOp);
                        getHarness().addBulkLoadedComponent(loadOp);
                    }
                }
            } finally {
                ioOpCallback.completed(loadOp);
            }
        }

        @Override
        public void delete(ITupleReference tuple) throws HyracksDataException {
            componentBulkLoader.delete(tuple);
        }

        @Override
        public void abort() throws HyracksDataException {
            try {
                try {
                    componentBulkLoader.abort();
                } finally {
                    ioOpCallback.afterFinalize(loadOp);
                }
            } finally {
                ioOpCallback.completed(loadOp);
            }
        }

        @Override
        public void writeFailed(ICachedPage page, Throwable failure) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasFailed() {
            return componentBulkLoader.hasFailed();
        }

        @Override
        public Throwable getFailure() {
            return componentBulkLoader.getFailure();
        }
    }
