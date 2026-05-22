@InterfaceAudience.Public
@InterfaceStability.Unstable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuxServiceRecords {
  private List<AuxServiceRecord> services = new ArrayList<>();

  public AuxServiceRecords serviceList(AuxServiceRecord... serviceList) {
    for (AuxServiceRecord service : serviceList) {
      this.services.add(service);
    }
    return this;
  }

  public List<AuxServiceRecord> getServices() {
    return services;
  }
}
