public class Snippet__4737348 {

    public Snippet__4737348(NIOLooper s, String streamManagerHost, int streamManagerPort,
                                 String topologyName, String topologyId,
                                 PhysicalPlans.Instance instance,
                                 Communicator<Message> inStreamQueue,
                                 Communicator<Message> outStreamQueue,
                                 Communicator<InstanceControlMsg> inControlQueue,
                                 HeronSocketOptions options,
                                 GatewayMetrics gatewayMetrics) {
        super(s, streamManagerHost, streamManagerPort, options);

        this.topologyName = topologyName;
        this.topologyId = topologyId;

        this.instance = instance;
        this.inStreamQueue = inStreamQueue;
        this.outStreamQueue = outStreamQueue;
        this.inControlQueue = inControlQueue;

        this.systemConfig =
            (SystemConfig) SingletonRegistry.INSTANCE.getSingleton(SystemConfig.HERON_SYSTEM_CONFIG);

        this.gatewayMetrics = gatewayMetrics;

        addStreamManagerClientTasksOnWakeUp();
      }

}
