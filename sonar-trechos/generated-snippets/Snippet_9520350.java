public class OSS extends DelegateToFileSystem {

  public OSS(URI theUri, Configuration conf)
          throws IOException, URISyntaxException {
    super(theUri, new AliyunOSSFileSystem(), conf, "oss", false);
  }

  @Override
  public int getUriDefaultPort() {
    return Constants.OSS_DEFAULT_PORT;
  }
}
