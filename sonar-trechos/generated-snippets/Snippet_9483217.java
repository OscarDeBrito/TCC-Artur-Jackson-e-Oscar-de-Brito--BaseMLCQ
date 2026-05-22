static class AuxiliaryLocalPathHandlerImpl
      implements AuxiliaryLocalPathHandler {
    private LocalDirsHandlerService dirhandlerService;
    AuxiliaryLocalPathHandlerImpl(
        LocalDirsHandlerService dirhandlerService) {
      this.dirhandlerService = dirhandlerService;
    }

    @Override
    public Path getLocalPathForRead(String path) throws IOException {
      return dirhandlerService.getLocalPathForRead(path);
    }

    @Override
    public Path getLocalPathForWrite(String path) throws IOException {
      return dirhandlerService.getLocalPathForWrite(path);
    }

    @Override
    public Path getLocalPathForWrite(String path, long size)
        throws IOException {
      return dirhandlerService.getLocalPathForWrite(path, size, false);
    }
  }
