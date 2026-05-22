public class DefaultSdk extends Sdk {

    private final String androidVersion;
    private final String robolectricVersion;
    private final String codeName;
    private final int requiredJavaVersion;
    private Path jarPath;

    public DefaultSdk(
        int apiLevel,
        String androidVersion,
        String robolectricVersion,
        String codeName,
        int requiredJavaVersion) {
      super(apiLevel);
      this.androidVersion = androidVersion;
      this.robolectricVersion = robolectricVersion;
      this.codeName = codeName;
      this.requiredJavaVersion = requiredJavaVersion;
      Preconditions.checkNotNull(dependencyResolver);
    }

    @Override
    public String getAndroidVersion() {
      return androidVersion;
    }

    @Override
    public String getAndroidCodeName() {
      return codeName;
    }

    private DependencyJar getAndroidSdkDependency() {
      if (!isSupported()) {
        throw new UnsupportedClassVersionError(getUnsupportedMessage());
      }

      return new DependencyJar("org.robolectric",
          "android-all",
          getAndroidVersion() + "-robolectric-" + robolectricVersion, null);
    }

    @Override
    public synchronized Path getJarPath() {
      if (jarPath == null) {
        URL url = dependencyResolver.getLocalArtifactUrl(getAndroidSdkDependency());
        jarPath = Util.pathFrom(url);

        if (!Files.exists(jarPath)) {
          throw new RuntimeException("SDK " + getApiLevel() + " jar not present at " + jarPath);
        }
      }
      return jarPath;
    }

    @Override
    public boolean isSupported() {
      return requiredJavaVersion <= RUNNING_JAVA_VERSION;
    }

    @Override
    public String getUnsupportedMessage() {
      return String.format(
          Locale.getDefault(),
          "Android SDK %d requires Java %d (have Java %d)",
          getApiLevel(),
          requiredJavaVersion,
          RUNNING_JAVA_VERSION);
    }
  }
