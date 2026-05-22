public static class StopException extends ServerCommandException {

        public StopException(final File home, final int returnCode, final String... args) {
            super(home, returnCode, args);
        }
    }
