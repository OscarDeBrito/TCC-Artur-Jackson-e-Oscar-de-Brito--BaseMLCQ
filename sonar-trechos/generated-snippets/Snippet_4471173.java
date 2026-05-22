public interface CompleteRpcMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:exec.rpc.CompleteRpcMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>optional .exec.rpc.RpcHeader header = 1;</code>
     */
    boolean hasHeader();
    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>optional .exec.rpc.RpcHeader header = 1;</code>
     */
    org.apache.drill.exec.proto.GeneralRPCProtos.RpcHeader getHeader();
    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>optional .exec.rpc.RpcHeader header = 1;</code>
     */
    org.apache.drill.exec.proto.GeneralRPCProtos.RpcHeaderOrBuilder getHeaderOrBuilder();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>optional bytes protobuf_body = 2;</code>
     */
    boolean hasProtobufBody();
    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>optional bytes protobuf_body = 2;</code>
     */
    com.google.protobuf.ByteString getProtobufBody();

    /**
     * <pre>
     * optional
     * </pre>
     *
     * <code>optional bytes raw_body = 3;</code>
     */
    boolean hasRawBody();
    /**
     * <pre>
     * optional
     * </pre>
     *
     * <code>optional bytes raw_body = 3;</code>
     */
    com.google.protobuf.ByteString getRawBody();
  }
