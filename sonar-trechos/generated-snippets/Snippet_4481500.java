private class ClassBytesCacheLoader extends CacheLoader<String, byte[]> {
    @Override
    public byte[] load(String path) throws ClassTransformationException, IOException {
      URL u = this.getClass().getResource(path);
      if (u == null) {
        throw new ClassTransformationException(String.format("Unable to find TemplateClass at path %s", path));
      }
      return Resources.toByteArray(u);
    }
  };
