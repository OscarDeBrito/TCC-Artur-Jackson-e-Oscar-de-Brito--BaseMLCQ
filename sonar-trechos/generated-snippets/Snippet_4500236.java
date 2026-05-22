public interface PutOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required .FlumeEvent event = 1;
    /**
     * <code>required .FlumeEvent event = 1;</code>
     */
    boolean hasEvent();
    /**
     * <code>required .FlumeEvent event = 1;</code>
     */
    org.apache.flume.channel.file.proto.ProtosFactory.FlumeEvent getEvent();
    /**
     * <code>required .FlumeEvent event = 1;</code>
     */
    org.apache.flume.channel.file.proto.ProtosFactory.FlumeEventOrBuilder getEventOrBuilder();

    // optional sfixed64 checksum = 2;
    /**
     * <code>optional sfixed64 checksum = 2;</code>
     */
    boolean hasChecksum();
    /**
     * <code>optional sfixed64 checksum = 2;</code>
     */
    long getChecksum();
  }
