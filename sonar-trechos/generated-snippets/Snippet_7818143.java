public class TailLogRequest extends AdminRequest {
  public static TailLogRequest create() {
    TailLogRequest m = new TailLogRequest();
    return m;
  }

  @Override
  public AdminResponse createResponse(DistributionManager dm) {
    return TailLogResponse.create(dm, this.getSender());
  }

  public TailLogRequest() {
    friendlyName = "Tail system log";
  }

  @Override
  public int getDSFID() {
    return TAIL_LOG_REQUEST;
  }

  @Override
  public void toData(DataOutput out) throws IOException {
    super.toData(out);
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    super.fromData(in);
  }

  @Override
  public String toString() {
    return "TailLogRequest from " + this.getRecipient();
  }
}
