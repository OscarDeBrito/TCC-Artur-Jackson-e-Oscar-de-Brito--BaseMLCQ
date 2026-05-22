@XmlRootElement(name = "renData")
@XmlType(propOrder = {"currency", "fees"})
public class FeeRenewResponseExtensionV06 extends FeeTransformResponseExtension {

  /** This version of the extension doesn't support the "credit" field. */
  @Override
  public ImmutableList<Credit> getCredits() {
    return super.getCredits();
  }
}
