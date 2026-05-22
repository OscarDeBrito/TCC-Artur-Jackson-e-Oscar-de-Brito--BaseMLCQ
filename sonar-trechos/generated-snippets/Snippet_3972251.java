private static class FileExtensionFilter implements FilenameFilter {
    private String extension;

    private FileExtensionFilter(String extension) {
      this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
      return name.endsWith(this.extension);
    }
  }
