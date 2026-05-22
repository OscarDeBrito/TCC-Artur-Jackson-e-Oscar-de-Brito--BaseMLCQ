public class Snippet__8845708 {

    public org.apache.airavata.sharing.registry.models.Entity getResult() throws org.apache.airavata.sharing.registry.models.SharingRegistryException, org.apache.thrift.TException {
            if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
              throw new IllegalStateException("Method call not finished!");
            }
            org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
            org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
            return (new Client(prot)).recv_getEntity();
          }

}
