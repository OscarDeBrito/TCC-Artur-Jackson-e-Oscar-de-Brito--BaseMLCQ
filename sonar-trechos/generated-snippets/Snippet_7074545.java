private static class VersionAction implements Action<Project> {
        private final Version version;

        public VersionAction(Version version) {
            this.version = version;
        }

        @Override
        public void execute(Project project) {
            project.setVersion(version);
        }
    }
