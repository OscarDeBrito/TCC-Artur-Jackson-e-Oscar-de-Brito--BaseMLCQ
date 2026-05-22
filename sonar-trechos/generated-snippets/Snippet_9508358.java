public abstract class KeyProviderExtension
<E extends KeyProviderExtension.Extension> extends KeyProvider {

  /**
   * A marker interface for the KeyProviderExtension subclass implement.
   */
  public static interface Extension {
  }

  private KeyProvider keyProvider;
  private E extension;

  public KeyProviderExtension(KeyProvider keyProvider, E extensions) {
    super(keyProvider.getConf());
    this.keyProvider = keyProvider;
    this.extension = extensions;
  }
  
  protected E getExtension() {
    return extension;
  }
  
  protected KeyProvider getKeyProvider() {
    return keyProvider;
  }

  @Override
  public boolean isTransient() {
    return keyProvider.isTransient();
  }

  @Override
  public Metadata[] getKeysMetadata(String... names) throws IOException {
    return keyProvider.getKeysMetadata(names);
  }

  @Override
  public KeyVersion getCurrentKey(String name) throws IOException {
    return keyProvider.getCurrentKey(name);
  }

  @Override
  public KeyVersion createKey(String name, Options options)
      throws NoSuchAlgorithmException, IOException {
    return keyProvider.createKey(name, options);
  }

  @Override
  public KeyVersion rollNewVersion(String name)
      throws NoSuchAlgorithmException, IOException {
    return keyProvider.rollNewVersion(name);
  }

  @Override
  public KeyVersion getKeyVersion(String versionName) throws IOException {
    return keyProvider.getKeyVersion(versionName);
  }

  @Override
  public List<String> getKeys() throws IOException {
    return keyProvider.getKeys();
  }

  @Override
  public List<KeyVersion> getKeyVersions(String name) throws IOException {
    return keyProvider.getKeyVersions(name);
  }

  @Override
  public Metadata getMetadata(String name) throws IOException {
    return keyProvider.getMetadata(name);
  }

  @Override
  public KeyVersion createKey(String name, byte[] material, Options options)
      throws IOException {
    return keyProvider.createKey(name, material, options);
  }

  @Override
  public void deleteKey(String name) throws IOException {
    keyProvider.deleteKey(name);
  }

  @Override
  public KeyVersion rollNewVersion(String name, byte[] material)
      throws IOException {
    return keyProvider.rollNewVersion(name, material);
  }

  @Override
  public void invalidateCache(String name) throws IOException {
    keyProvider.invalidateCache(name);
  }

  @Override
  public void flush() throws IOException {
    keyProvider.flush();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ": " + keyProvider.toString();
  }
}
