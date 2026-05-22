public static class cancelCompaction_call extends org.apache.thrift.async.TAsyncMethodCall<Void> {
      private java.nio.ByteBuffer login;
      private java.lang.String tableName;
      public cancelCompaction_call(java.nio.ByteBuffer login, java.lang.String tableName, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.login = login;
        this.tableName = tableName;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("cancelCompaction", org.apache.thrift.protocol.TMessageType.CALL, 0));
        cancelCompaction_args args = new cancelCompaction_args();
        args.setLogin(login);
        args.setTableName(tableName);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public Void getResult() throws AccumuloSecurityException, TableNotFoundException, AccumuloException, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new java.lang.IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return null;
      }
    }
