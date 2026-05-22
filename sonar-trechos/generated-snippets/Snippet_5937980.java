@InterfaceAudience.Public
    public static class InvalidACLException extends KeeperException {
        public InvalidACLException() {
            super(Code.INVALIDACL);
        }
        public InvalidACLException(String path) {
            super(Code.INVALIDACL, path);
        }
    }
