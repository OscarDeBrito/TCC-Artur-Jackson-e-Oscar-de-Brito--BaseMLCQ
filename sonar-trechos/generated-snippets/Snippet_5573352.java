public class EdgeInvocationMeters extends ConsumerInvocationMeters {
  public EdgeInvocationMeters(Registry registry) {
    super(registry);
  }

  @Override
  protected AbstractInvocationMeter createMeter(Id id) {
    return new EdgeInvocationMeter(registry, id);
  }
}
