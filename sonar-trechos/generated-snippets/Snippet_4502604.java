public class FluoOracleImpl implements FluoOracle {

  private static final Logger log = LoggerFactory.getLogger(FluoOracleImpl.class);

  private FluoConfiguration config;
  private Environment env;
  private AutoCloseable reporters;
  private OracleServer oracleServer;
  private NodeCache appIdCache;

  public FluoOracleImpl(FluoConfiguration connConfig) {
    Objects.requireNonNull(connConfig);
    Preconditions.checkArgument(connConfig.hasRequiredConnectionProps());
    config = FluoAdminImpl.mergeZookeeperConfig(connConfig);
    Preconditions.checkArgument(config.hasRequiredOracleProps());
    // any client in oracle should retry forever
    config.setConnectionRetryTimeout(-1);
    try {
      config.validate();
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid FluoConfiguration", e);
    }
  }

  @Override
  public void start() {
    try {
      env = new Environment(config);
      reporters = ReporterUtil.setupReporters(env);
      appIdCache = CuratorUtil.startAppIdWatcher(env);

      log.info("Starting Oracle for Fluo '{}' application with the following configuration:",
          config.getApplicationName());
      env.getConfiguration().print();

      oracleServer = new OracleServer(env);
      oracleServer.start();
    } catch (Exception e) {
      throw new FluoException(e);
    }
  }

  @Override
  public void stop() {
    try {
      oracleServer.stop();
      appIdCache.close();
      reporters.close();
      env.close();
    } catch (Exception e) {
      throw new FluoException(e);
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: FluoOracleImpl <fluoPropsPath>");
      System.exit(-1);
    }
    String propsPath = args[0];
    Objects.requireNonNull(propsPath);
    File propsFile = new File(propsPath);
    if (!propsFile.exists()) {
      System.err.println("ERROR - Fluo properties file does not exist: " + propsPath);
      System.exit(-1);
    }
    Preconditions.checkArgument(propsFile.exists());
    try {
      FluoConfiguration config = new FluoConfiguration(propsFile);
      FluoOracleImpl oracle = new FluoOracleImpl(config);
      oracle.start();
      while (true) {
        UtilWaitThread.sleep(10000);
      }
    } catch (Exception e) {
      log.error("Exception running FluoOracle: ", e);
    }
  }
}
