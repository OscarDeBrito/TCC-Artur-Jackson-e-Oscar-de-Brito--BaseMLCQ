public class SftpServerSubSystemEventListener extends ServerEventListenerHelper implements SftpEventListener {
    public SftpServerSubSystemEventListener(Appendable stdout, Appendable stderr) {
        super(SftpConstants.SFTP_SUBSYSTEM_NAME,  stdout, stderr);
    }

    @Override
    public void initialized(ServerSession session, int version) throws IOException {
        outputDebugMessage("Session %s initialized - version=%d", session, version);
    }

    @Override
    public void destroying(ServerSession session) throws IOException {
        outputDebugMessage("Session destroyed: %s", session);
    }

    @Override
    public void created(
            ServerSession session, Path path, Map<String, ?> attrs, Throwable thrown)
                throws IOException {
        if (thrown == null) {
            outputDebugMessage("Session %s created directory %s with attributes=%s", session, path, attrs);
        } else {
            outputErrorMessage("Failed (%s) to create directory %s in session %s: %s",
                thrown.getClass().getSimpleName(), path, session, thrown.getMessage());
        }
    }

    @Override
    public void moved(
            ServerSession session, Path srcPath, Path dstPath, Collection<CopyOption> opts, Throwable thrown)
                throws IOException {
        if (thrown == null) {
            outputDebugMessage("Session %s moved %s to %s with options=%s",
                session, srcPath, dstPath, opts);
        } else {
            outputErrorMessage("Failed (%s) to move %s to %s using options=%s in session %s: %s",
                thrown.getClass().getSimpleName(), srcPath, dstPath, opts, session, thrown.getMessage());
        }
    }

    @Override
    public void removed(ServerSession session, Path path, Throwable thrown) throws IOException {
        if (thrown == null) {
            outputDebugMessage("Session %s removed %s", session, path);
        } else {
            outputErrorMessage("Failed (%s) to remove %s in session %s: %s",
                thrown.getClass().getSimpleName(), path, session, thrown.getMessage());
        }
    }
}
