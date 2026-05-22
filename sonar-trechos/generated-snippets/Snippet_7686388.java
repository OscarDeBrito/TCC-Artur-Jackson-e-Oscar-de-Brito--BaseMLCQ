public class KafkaZKOptionMixin {

	private String zkconnect = "localhost:2181";

	private int zksessionTimeout = 6000;

	private int zkconnectionTimeout = 6000;

	private int zksyncTime = 2000;

	public String getZkconnect() {
		return zkconnect;
	}

	@ModuleOption("zookeeper connect string")
	public void setZkconnect(String zkconnect) {
		this.zkconnect = zkconnect;
	}

	public int getZksessionTimeout() {
		return zksessionTimeout;
	}

	@ModuleOption("zookeeper session timeout in milliseconds")
	public void setZksessionTimeout(int zksessionTimeout) {
		this.zksessionTimeout = zksessionTimeout;
	}

	public int getZkconnectionTimeout() {
		return zkconnectionTimeout;
	}

	@ModuleOption("the max time the client waits to connect to ZK in milliseconds")
	public void setZkconnectionTimeout(int zkconnectionTimeout) {
		this.zkconnectionTimeout = zkconnectionTimeout;
	}

	public int getZksyncTime() {
		return zksyncTime;
	}

	@ModuleOption("how far a ZK follower can be behind a ZK leader in milliseconds")
	public void setZksyncTime(int zksyncTime) {
		this.zksyncTime = zksyncTime;
	}
}
