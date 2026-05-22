public class Snippet__8765693 {

    public static RMNode newNodeInfo(String rackName, String hostName,
                                  final ResourceOption resourceOption, int port) {
        final NodeId nodeId = newNodeID(hostName, port);
        final String nodeAddr = hostName + ":" + port;
        final String httpAddress = hostName;
    
        return new FakeRMNodeImpl(nodeId, nodeAddr, httpAddress,
            resourceOption, rackName, "Me good",
            port, hostName, null);
      }

}
