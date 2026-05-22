public static class Basic extends ProjectProfilingSupportProvider {

        @Override
        public boolean isProfilingSupported() {
            return false;
        }

        @Override
        public boolean isAttachSupported() {
            return false;
        }

        @Override
        public boolean isFileObjectSupported(FileObject fo) {
            return false;
        }
        
        @Override
        public boolean areProfilingPointsSupported() {
            return false;
        }

        @Override
        public JavaPlatform getProjectJavaPlatform() {
            return null;
        }

        @Override
        public boolean checkProjectCanBeProfiled(FileObject profiledClassFile) {
            return true;
        }

        @Override
        public void setupProjectSessionSettings(SessionSettings ss) {
        }

        @Override
        public boolean startProfilingSession(FileObject profiledClassFile, boolean isTest) {
            return false;
        }
        
    }
