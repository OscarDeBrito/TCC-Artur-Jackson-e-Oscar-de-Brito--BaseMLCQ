static class TcpSessionImpl implements Column {
    @Override
    public TypeProtos.MajorType getMinorType() {
      return Types.optional(TypeProtos.MinorType.BIGINT);
    }

    @Override
    public void process(IEnhancedPacketBLock block, ValueVector vv, int count) {
      PacketDecoder packet = new PacketDecoder();
      if (packet.readPcapng(block.getPacketData())) {
        setNullableLongColumnValue(packet.getSessionHash(), vv, count);
      }
    }
  }
