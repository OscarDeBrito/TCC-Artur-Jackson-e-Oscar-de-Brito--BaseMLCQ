public static class get_call extends org.apache.thrift.async.TAsyncMethodCall<TResult> {
      private java.nio.ByteBuffer table;
      private TGet tget;
      public get_call(java.nio.ByteBuffer table, TGet tget, org.apache.thrift.async.AsyncMethodCallback<TResult> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.table = table;
        this.tget = tget;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("get", org.apache.thrift.protocol.TMessageType.CALL, 0));
        get_args args = new get_args();
        args.setTable(table);
        args.setTget(tget);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public TResult getResult() throws TIOError, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new java.lang.IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_get();
      }
    }
