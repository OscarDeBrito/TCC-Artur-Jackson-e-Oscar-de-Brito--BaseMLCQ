public static class getNettyMetricSizeByHost_call extends org.apache.thrift.async.TAsyncMethodCall {
      private String topologyId;
      private String host;
      public getNettyMetricSizeByHost_call(String topologyId, String host, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.topologyId = topologyId;
        this.host = host;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getNettyMetricSizeByHost", org.apache.thrift.protocol.TMessageType.CALL, 0));
        getNettyMetricSizeByHost_args args = new getNettyMetricSizeByHost_args();
        args.set_topologyId(topologyId);
        args.set_host(host);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public int getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_getNettyMetricSizeByHost();
      }
    }
