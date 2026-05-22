@InterfaceAudience.Private
@InterfaceStability.Evolving
public class RecoveryInProgressException extends IOException {
  private static final long serialVersionUID = 1L;

  public RecoveryInProgressException(String msg) {
    super(msg);
  }
}
