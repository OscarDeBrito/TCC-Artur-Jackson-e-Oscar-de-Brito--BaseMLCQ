public class RMFatalEvent extends AbstractEvent<RMFatalEventType> {
  private String cause;

  public RMFatalEvent(RMFatalEventType rmFatalEventType, String cause) {
    super(rmFatalEventType);
    this.cause = cause;
  }

  public RMFatalEvent(RMFatalEventType rmFatalEventType, Exception cause) {
    super(rmFatalEventType);
    this.cause = StringUtils.stringifyException(cause);
  }

  public String getCause() {return this.cause;}
}
