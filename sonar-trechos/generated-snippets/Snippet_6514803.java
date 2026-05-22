@SuppressWarnings("restriction")
class OfflineTransport extends Transport {

    private static final Status OFFLINE_STATUS = new Status(IStatus.ERROR, Activator.PLUGIN_ID, "offline");

    @Override
    public IStatus download(URI toDownload, OutputStream target, long startPos, IProgressMonitor monitor) {
        throw new IllegalStateException("no download in offline mode");
    }

    /*
     * This is the only method expected to be called in offline mode.
     * 
     * @see AbstractRepositoryManager#loadIndexFile(URI location, IProgressMonitor monitor)
     */
    @Override
    public IStatus download(URI toDownload, OutputStream target, IProgressMonitor monitor) {
        return OFFLINE_STATUS;
    }

    @Override
    public InputStream stream(URI toDownload, IProgressMonitor monitor) throws FileNotFoundException, CoreException,
            AuthenticationFailedException {
        throw new IllegalStateException("no download in offline mode");
    }

    @Override
    public long getLastModified(URI toDownload, IProgressMonitor monitor) throws CoreException, FileNotFoundException,
            AuthenticationFailedException {
        throw new IllegalStateException("no download in offline mode");
    }

}
