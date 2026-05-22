@Singleton
class RuntimeLogHandler {

  @Inject
  RuntimeLogHandler(RequestHandlerConfigurator configurator, EventBus eventBus) {
    configurator
        .newConfiguration()
        .methodName(RUNTIME_LOG_METHOD)
        .paramsAsDto(RuntimeLogEvent.class)
        .noResult()
        .withBiConsumer(
            (endpointId, log) ->
                eventBus.fireEvent(
                    new EnvironmentOutputEvent(log.getText(), log.getMachineName())));
  }
}
