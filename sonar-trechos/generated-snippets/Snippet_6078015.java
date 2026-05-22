public static class ModuleDependencyAnalysisError extends AnalysisError {
        
        private boolean isError = true;
        
        public ModuleDependencyAnalysisError(Node treeNode, String message, int code) {
            super(treeNode, message, code);
        }
        
        public ModuleDependencyAnalysisError(Node treeNode, String message, int code, Backend backend) {
            super(treeNode, message, code, backend);
        }
        
        public ModuleDependencyAnalysisError(Node treeNode, String message) {
            super(treeNode, message);
        }

        public ModuleDependencyAnalysisError(Node treeNode, String message, boolean isError) {
            super(treeNode, message);
            this.isError = isError;
        }
        
        public ModuleDependencyAnalysisError(Node treeNode, String message, Backend backend) {
            super(treeNode, message, backend);
        }
        
        @Override
        public boolean isWarning() {
            return !isError;
        }
    }
