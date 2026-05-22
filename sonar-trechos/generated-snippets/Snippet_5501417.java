public final class HelloREEFEnvironment {

  private static final Logger LOG = Logger.getLogger(HelloREEFEnvironment.class.getName());

  private static final Configuration DRIVER_CONFIG = DriverConfiguration.CONF
      .set(DriverConfiguration.DRIVER_IDENTIFIER, "HelloREEF_Env")
      .set(DriverConfiguration.GLOBAL_LIBRARIES, EnvironmentUtils.getClassLocation(HelloDriver.class))
      .set(DriverConfiguration.ON_DRIVER_STARTED, HelloDriver.StartHandler.class)
      .set(DriverConfiguration.ON_EVALUATOR_ALLOCATED, HelloDriver.EvaluatorAllocatedHandler.class)
      .build();

  private static final Configuration LOCAL_DRIVER_MODULE = LocalDriverConfiguration.CONF
      .set(LocalDriverConfiguration.RUNTIME_NAMES, RuntimeIdentifier.RUNTIME_NAME)
      .set(LocalDriverConfiguration.CLIENT_REMOTE_IDENTIFIER, ClientRemoteIdentifier.NONE)
      .set(LocalDriverConfiguration.JOB_IDENTIFIER, "LOCAL_ENV_DRIVER_TEST")
      .set(LocalDriverConfiguration.ROOT_FOLDER, "./REEF_LOCAL_RUNTIME")
      .set(LocalDriverConfiguration.MAX_NUMBER_OF_EVALUATORS, 2)
      .set(LocalDriverConfiguration.JVM_HEAP_SLACK, 0.0)
      .build();

  private static final Configuration ENVIRONMENT_CONFIG =
      Tang.Factory.getTang().newConfigurationBuilder()
          .bindNamedParameter(RemoteConfiguration.ManagerName.class, "REEF_ENVIRONMENT")
          .bindNamedParameter(RemoteConfiguration.MessageCodec.class, REEFMessageCodec.class)
          .build();

  /**
   * Start Hello REEF job with Driver and Client sharing the same process.
   *
   * @param args command line parameters - not used.
   * @throws InjectionException configuration error.
   */
  public static void main(final String[] args) throws InjectionException {

    try (final REEFEnvironment reef = REEFEnvironment.fromConfiguration(
        LOCAL_DRIVER_MODULE, DRIVER_CONFIG, ENVIRONMENT_CONFIG)) {
      reef.run();
      final ReefServiceProtos.JobStatusProto status = reef.getLastStatus();
      LOG.log(Level.INFO, "REEF job completed: {0}", status);
    }
  }

  /**
   * Empty private constructor to prohibit instantiation of all-static class.
   */
  private HelloREEFEnvironment() {
  }
}
