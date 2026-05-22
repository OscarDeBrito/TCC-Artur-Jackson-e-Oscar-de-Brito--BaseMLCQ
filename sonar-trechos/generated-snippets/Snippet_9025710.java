public class SSLCredentialProviderFactory {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  public static final String DEFAULT_PROVIDER_CHAIN = "env;sysprop";
  public static final String PROVIDER_CHAIN_KEY = "solr.ssl.credential.provider.chain";

  private final static ImmutableMap<String, Class> defaultProviders = ImmutableMap.of(
      "env", EnvSSLCredentialProvider.class,
      "sysprop", SysPropSSLCredentialProvider.class,
      "hadoop", HadoopSSLCredentialProvider.class
  );

  private String providerChain;

  public SSLCredentialProviderFactory() {
    this.providerChain = System.getProperty(PROVIDER_CHAIN_KEY, DEFAULT_PROVIDER_CHAIN);
  }

  public SSLCredentialProviderFactory(String providerChain) {
    this.providerChain = providerChain;
  }

  public List<SSLCredentialProvider> getProviders() {
    ArrayList<SSLCredentialProvider> providers = new ArrayList<>();
    log.info(String.format(Locale.ROOT, "Processing SSL Credential Provider chain: %s", providerChain));
    String classPrefix = "class://";
    for (String provider : providerChain.split(";")) {
      if (defaultProviders.containsKey(provider)) {
        providers.add(getDefaultProvider(defaultProviders.get(provider)));
      } else if (provider.startsWith(classPrefix)) {
        providers.add(getProviderByClassName(provider.substring(classPrefix.length())));
      } else {
        throw new RuntimeException("Unable to parse credential provider: " + provider);
      }
    }
    return providers;
  }

  private SSLCredentialProvider getProviderByClassName(String clazzName) {
    try {
      return (SSLCredentialProvider) Class.forName(clazzName).getConstructor().newInstance();
    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      String msg = String.format(Locale.ROOT, "Could not instantiate %s credential provider", clazzName);
      log.error(msg);
      throw new RuntimeException(msg, e);
    }
  }

  private SSLCredentialProvider getDefaultProvider(Class aClass) {
    try {
      return (SSLCredentialProvider) aClass.getConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      String msg = String.format(Locale.ROOT, "Could not instantiate %s credential provider", aClass.getName());
      log.error(msg);
      throw new RuntimeException(msg, e);
    }
  }

}
