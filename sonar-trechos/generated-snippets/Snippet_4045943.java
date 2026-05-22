class NeverDeduplicator implements UnboundedReadDeduplicator {
    /** Create a new {@link NeverDeduplicator}. */
    public static UnboundedReadDeduplicator create() {
      return new NeverDeduplicator();
    }

    @Override
    public boolean shouldOutput(byte[] recordId) {
      return true;
    }
  }
