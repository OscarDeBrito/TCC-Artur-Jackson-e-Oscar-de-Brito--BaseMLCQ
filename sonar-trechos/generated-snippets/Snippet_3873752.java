public static class invalidateConditionalUpdate_call extends org.apache.thrift.async.TAsyncMethodCall<Void> {
      private org.apache.accumulo.core.trace.thrift.TInfo tinfo;
      private long sessID;
      public invalidateConditionalUpdate_call(org.apache.accumulo.core.trace.thrift.TInfo tinfo, long sessID, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.tinfo = tinfo;
        this.sessID = sessID;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("invalidateConditionalUpdate", org.apache.thrift.protocol.TMessageType.CALL, 0));
        invalidateConditionalUpdate_args args = new invalidateConditionalUpdate_args();
        args.setTinfo(tinfo);
        args.setSessID(sessID);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public Void getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new java.lang.IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return null;
      }
    }
