@SuppressWarnings("serial")
public class GatewayDeltaCreateEvent extends AbstractGatewayDeltaEvent {

  private byte[] gatewayDelta;

  public GatewayDeltaCreateEvent() {}

  public GatewayDeltaCreateEvent(String regionName, String key, byte[] gatewayDelta) {
    super(regionName, key);
    this.gatewayDelta = gatewayDelta;
  }

  public byte[] getGatewayDelta() {
    return this.gatewayDelta;
  }

  @Override
  public void apply(Cache cache) {
    Region<String, CachedDeserializable> region = getRegion(cache);
    region.put(this.key,
        CachedDeserializableFactory.create(this.gatewayDelta, (InternalCache) cache), true);
    if (cache.getLogger().fineEnabled()) {
      StringBuilder builder = new StringBuilder();
      builder.append("Applied ").append(this);
      cache.getLogger().fine(builder.toString());
    }
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    super.fromData(in);
    this.gatewayDelta = DataSerializer.readByteArray(in);
  }

  @Override
  public void toData(DataOutput out) throws IOException {
    super.toData(out);
    DataSerializer.writeByteArray(this.gatewayDelta, out);
  }

  public String toString() {
    return new StringBuilder().append("GatewayDeltaCreateEvent[").append("regionName=")
        .append(this.regionName).append("; key=").append(this.key).append("; gatewayDelta=")
        .append(Arrays.toString(this.gatewayDelta)).append("]").toString();
  }
}
