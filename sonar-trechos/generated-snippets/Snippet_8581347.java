public static class HFileFilter extends AbstractFileStatusFilter {
    final FileSystem fs;

    public HFileFilter(FileSystem fs) {
      this.fs = fs;
    }

    @Override
    protected boolean accept(Path p, @CheckForNull Boolean isDir) {
      if (!StoreFileInfo.isHFile(p)) {
        return false;
      }

      try {
        return isFile(fs, isDir, p);
      } catch (IOException ioe) {
        // Maybe the file was moved or the fs was disconnected.
        LOG.warn("Skipping file " + p +" due to IOException", ioe);
        return false;
      }
    }
  }
