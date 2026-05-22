@InterfaceAudience.Private
public interface PeerProcedureHandler {

  void addPeer(String peerId) throws ReplicationException, IOException;

  void removePeer(String peerId) throws ReplicationException, IOException;

  void disablePeer(String peerId) throws ReplicationException, IOException;

  void enablePeer(String peerId) throws ReplicationException, IOException;

  void updatePeerConfig(String peerId) throws ReplicationException, IOException;

  void transitSyncReplicationPeerState(String peerId, int stage, HRegionServer rs)
      throws ReplicationException, IOException;
}
