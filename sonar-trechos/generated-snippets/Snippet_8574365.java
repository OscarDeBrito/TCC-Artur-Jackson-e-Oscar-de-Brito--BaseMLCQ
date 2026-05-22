public class Snippet__8574365 {

    public Snippet__8574365(java.nio.ByteBuffer tableName, java.util.List<BatchMutation> rowBatches, java.util.Map<java.nio.ByteBuffer,java.nio.ByteBuffer> attributes, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
            super(client, protocolFactory, transport, resultHandler, false);
            this.tableName = tableName;
            this.rowBatches = rowBatches;
            this.attributes = attributes;
          }

}
