public class ErrorFrame extends VinciFrame {

  public ErrorFrame(String error_message) {
    super();
    fadd(TransportConstants.ERROR_KEY, error_message);
  }

}
