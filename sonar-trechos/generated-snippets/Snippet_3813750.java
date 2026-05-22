public class ClusterControl implements AutoCloseable {

   private Channel clusterChannel;

   private final ClientSessionFactoryInternal sessionFactory;

   private final ActiveMQServer server;

   private final String clusterUser;

   private final String clusterPassword;

   public ClusterControl(ClientSessionFactoryInternal sessionFactory, ActiveMQServer server) {
      this.sessionFactory = sessionFactory;
      this.server = server;
      this.clusterUser = server.getConfiguration().getClusterUser();
      this.clusterPassword = server.getConfiguration().getClusterPassword();
   }

   /**
    * authorise this cluster control so it can communicate with the cluster, it will set the cluster channel on a successful
    * authentication.
    *
    * @throws ActiveMQException if authorisation wasn't successful.
    */
   public void authorize() throws ActiveMQException {
      CoreRemotingConnection connection = (CoreRemotingConnection) sessionFactory.getConnection();

      clusterChannel = connection.getChannel(ChannelImpl.CHANNEL_ID.CLUSTER.id, -1);

      ClusterConnectReplyMessage packet = (ClusterConnectReplyMessage) clusterChannel.sendBlocking(new ClusterConnectMessage(clusterUser, clusterPassword), PacketImpl.CLUSTER_CONNECT_REPLY);

      if (!packet.isAuthorized()) {
         throw ActiveMQMessageBundle.BUNDLE.unableToValidateClusterUser(clusterUser);
      }
   }

   /**
    * XXX HORNETQ-720
    *
    * @param attemptingFailBack if {@code true} then this server wants to trigger a fail-back when
    *                           up-to-date, that is it wants to take over the role of 'live' from the current 'live'
    *                           server.
    * @throws ActiveMQException
    */
   public void announceReplicatingBackupToLive(final boolean attemptingFailBack,
                                               String replicationClusterName) throws ActiveMQException {

      ClusterConnectionConfiguration config = ConfigurationUtils.getReplicationClusterConfiguration(server.getConfiguration(), replicationClusterName);
      if (config == null) {
         ActiveMQServerLogger.LOGGER.announceBackupNoClusterConnections();
         throw new ActiveMQException("lacking cluster connection");

      }
      TransportConfiguration connector = server.getConfiguration().getConnectorConfigurations().get(config.getConnectorName());

      if (connector == null) {
         ActiveMQServerLogger.LOGGER.announceBackupNoConnector(config.getConnectorName());
         throw new ActiveMQException("lacking cluster connection");
      }

      clusterChannel.send(new BackupRegistrationMessage(connector, clusterUser, clusterPassword, attemptingFailBack));
   }

   /**
    * announce this node to the cluster.
    *
    * @param currentEventID     used if multiple announcements about this node are made.
    * @param nodeID             the node id if the announcing node
    * @param backupGroupName    the backup group name.
    * @param scaleDownGroupName the scaledown group name
    * @param isBackup           are we a backup
    * @param config             the transports config
    * @param backupConfig       the transports backup config
    */
   public void sendNodeAnnounce(final long currentEventID,
                                String nodeID,
                                String backupGroupName,
                                String scaleDownGroupName,
                                boolean isBackup,
                                TransportConfiguration config,
                                TransportConfiguration backupConfig) {
      clusterChannel.send(new NodeAnnounceMessage(currentEventID, nodeID, backupGroupName, scaleDownGroupName, isBackup, config, backupConfig));
   }

   /**
    * create a replication channel
    *
    * @return the replication channel
    */
   public Channel createReplicationChannel() {
      CoreRemotingConnection connection = (CoreRemotingConnection) sessionFactory.getConnection();
      return connection.getChannel(ChannelImpl.CHANNEL_ID.REPLICATION.id, -1);
   }

   /**
    * get the session factory used to connect to the cluster
    *
    * @return the session factory
    */
   public ClientSessionFactoryInternal getSessionFactory() {
      return sessionFactory;
   }

   /**
    * close this cluster control and its resources
    */
   @Override
   public void close() {
      sessionFactory.close();
   }

   public Vote sendQuorumVote(SimpleString handler, Vote vote) {
      try {
         ActiveMQServerLogger.LOGGER.sendingQuorumVoteRequest(getSessionFactory().getConnection().getRemoteAddress(), vote.toString());
         QuorumVoteReplyMessage replyMessage = (QuorumVoteReplyMessage) clusterChannel.sendBlocking(new QuorumVoteMessage(handler, vote), PacketImpl.QUORUM_VOTE_REPLY);
         QuorumVoteHandler voteHandler = server.getClusterManager().getQuorumManager().getVoteHandler(replyMessage.getHandler());
         replyMessage.decodeRest(voteHandler);
         Vote voteResponse = replyMessage.getVote();
         ActiveMQServerLogger.LOGGER.receivedQuorumVoteResponse(getSessionFactory().getConnection().getRemoteAddress(), voteResponse.toString());
         return voteResponse;
      } catch (ActiveMQException e) {
         return null;
      }
   }

   public boolean requestReplicatedBackup(int backupSize, SimpleString nodeID) {
      BackupRequestMessage backupRequestMessage = new BackupRequestMessage(backupSize, nodeID);
      return requestBackup(backupRequestMessage);
   }

   private boolean requestBackup(BackupRequestMessage backupRequestMessage) {
      BackupResponseMessage packet;
      try {
         packet = (BackupResponseMessage) clusterChannel.sendBlocking(backupRequestMessage, PacketImpl.BACKUP_REQUEST_RESPONSE);
      } catch (ActiveMQException e) {
         return false;
      }
      return packet.isBackupStarted();
   }

   public boolean requestSharedStoreBackup(int backupSize,
                                           String journalDirectory,
                                           String bindingsDirectory,
                                           String largeMessagesDirectory,
                                           String pagingDirectory) {
      BackupRequestMessage backupRequestMessage = new BackupRequestMessage(backupSize, journalDirectory, bindingsDirectory, largeMessagesDirectory, pagingDirectory);
      return requestBackup(backupRequestMessage);
   }

   public void announceScaleDown(SimpleString targetNodeId, SimpleString scaledDownNodeId) {
      ScaleDownAnnounceMessage announceMessage = new ScaleDownAnnounceMessage(targetNodeId, scaledDownNodeId);
      clusterChannel.send(announceMessage);
   }

   public String getClusterUser() {
      return clusterUser;
   }

   public String getClusterPassword() {
      return clusterPassword;
   }
}
