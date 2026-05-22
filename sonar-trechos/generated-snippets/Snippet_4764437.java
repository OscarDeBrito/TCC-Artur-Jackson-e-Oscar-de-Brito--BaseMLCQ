@AllArgsConstructor
  private static class CreateProxiedFileSystemFromProperties implements Callable<FileSystem> {
    @NonNull
    private final String userNameToProxyAs;
    @NonNull
    private final Properties properties;
    @NonNull
    private final URI uri;
    @NonNull
    private final Configuration configuration;
    private final FileSystem referenceFS;

    @Override
    public FileSystem call() throws Exception {
      FileSystem fs = ProxiedFileSystemUtils.createProxiedFileSystem(this.userNameToProxyAs, this.properties, this.uri,
          this.configuration);
      if (this.referenceFS != null) {
        return decorateFilesystemFromReferenceFS(fs, this.referenceFS);
      }
      return fs;
    }
  }
