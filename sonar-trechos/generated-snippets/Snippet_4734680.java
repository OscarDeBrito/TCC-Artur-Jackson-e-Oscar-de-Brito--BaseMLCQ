class ExceptionalRequestHandler implements HttpHandler {
  private static final Logger LOG = Logger.getLogger(ExceptionalRequestHandler.class.getName());
  private HttpHandler delegate;
  private Config runtime;
  private IScheduler scheduler;

  ExceptionalRequestHandler(HttpHandler delegate, Config runtime, IScheduler scheduler) {
    this.delegate = delegate;
    this.runtime = runtime;
    this.scheduler = scheduler;
  }

  @SuppressWarnings("IllegalCatch")
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try {
      delegate.handle(exchange);
      sendResponse(exchange, true);
    } catch (TerminateSchedulerException e) {
      sendResponse(exchange, true);

      // tell the scheduler to shutdown
      LOG.info("Request handler issuing a terminate request to scheduler");
      try {
        scheduler.close();
      } finally {
        Runtime.schedulerShutdown(runtime).terminate();
      }
    } catch (Exception e) {
      handleFailure(exchange, e);
    }
  }

  private static void handleFailure(HttpExchange exchange, Exception e) {
    LOG.log(Level.SEVERE,
        String.format("Failed to handle %s request", exchange.getRequestURI()), e);
    sendResponse(exchange, false);
  }

  private static void sendResponse(HttpExchange exchange, boolean success) {
    Scheduler.SchedulerResponse response = SchedulerUtils.constructSchedulerResponse(success);
    NetworkUtils.sendHttpResponse(exchange, response.toByteArray());
  }
}
