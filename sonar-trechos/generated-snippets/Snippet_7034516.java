public class Snippet__7034516 {

    private ServerErrorCode controlBlobStore(DataNodeId dataNodeId, PartitionId partitionId,
          short numReplicasCaughtUpPerPartition, boolean enable) throws IOException, TimeoutException {
        AdminRequest adminRequest =
            new AdminRequest(AdminRequestOrResponseType.BlobStoreControl, partitionId, correlationId.incrementAndGet(),
                CLIENT_ID);
        BlobStoreControlAdminRequest controlRequest =
            new BlobStoreControlAdminRequest(numReplicasCaughtUpPerPartition, enable, adminRequest);
        ByteBuffer responseBytes = sendRequestGetResponse(dataNodeId, controlRequest);
        AdminResponse adminResponse = AdminResponse.readFrom(new DataInputStream(new ByteBufferInputStream(responseBytes)));
        return adminResponse.getError();
      }

}
