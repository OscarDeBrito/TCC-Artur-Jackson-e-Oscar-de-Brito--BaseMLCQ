public static class LocalMessageManager<M extends Writable> extends
      AbstractMessageManager<M> {

    @SuppressWarnings("rawtypes")
    private static final ConcurrentHashMap<InetSocketAddress, LocalMessageManager> MANAGER_MAP = new ConcurrentHashMap<InetSocketAddress, LocalBSPRunner.LocalMessageManager>();
    private InetSocketAddress selfAddress;

    @Override
    public void init(TaskAttemptID attemptId, BSPPeer<?, ?, ?, ?, M> peer,
        HamaConfiguration conf, InetSocketAddress peerAddress) {
      super.init(attemptId, peer, conf, peerAddress);
      MANAGER_MAP.put(peerAddress, this);
      selfAddress = peerAddress;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void transfer(InetSocketAddress addr, BSPMessageBundle<M> bundle)
        throws IOException {
      // peer.incrementCounter(BSPPeerImpl.PeerCounter.TOTAL_MESSAGE_BYTES_TRANSFERED,
      // bundle.getLength());

      MANAGER_MAP.get(addr).localQueueForNextIteration.addBundle(bundle);
      peer.incrementCounter(BSPPeerImpl.PeerCounter.TOTAL_MESSAGES_RECEIVED,
          bundle.size());
    }

    @Override
    public InetSocketAddress getListenerAddress() {
      return selfAddress;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void transfer(InetSocketAddress addr, M msg) throws IOException {
      MANAGER_MAP.get(addr).localQueueForNextIteration.add(msg);
      peer.incrementCounter(BSPPeerImpl.PeerCounter.TOTAL_MESSAGES_RECEIVED, 1);
    }
  }
