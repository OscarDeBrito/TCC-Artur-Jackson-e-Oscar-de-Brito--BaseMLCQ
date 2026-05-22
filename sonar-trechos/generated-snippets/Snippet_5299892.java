public class HadoopFileSystemURLStreamHandler extends URLStreamHandler
    implements Configurable {

  private static Configuration defaultConf = new Configuration();

  public static Configuration getDefaultConf() {
    return defaultConf;
  }

  public static void setDefaultConf(Configuration defaultConf) {
    HadoopFileSystemURLStreamHandler.defaultConf = defaultConf;
  }

  private Configuration conf = defaultConf;

  @Override
  public void setConf(Configuration conf) {
    this.conf = conf;
  }

  @Override
  public Configuration getConf() {
    return conf;
  }

  @Override
  protected URLConnection openConnection(URL url) throws IOException {
    return new HadoopFileSystemURLConnection(url);
  }

  class HadoopFileSystemURLConnection extends URLConnection {
    public HadoopFileSystemURLConnection(URL url) {
      super(url);
    }
    @Override
    public void connect() throws IOException {
    }
    @Override
    public InputStream getInputStream() throws IOException {
      Path path = new Path(url.toExternalForm());
      FileSystem fileSystem = path.getFileSystem(conf);
      return fileSystem.open(path);
    }
  }
}
