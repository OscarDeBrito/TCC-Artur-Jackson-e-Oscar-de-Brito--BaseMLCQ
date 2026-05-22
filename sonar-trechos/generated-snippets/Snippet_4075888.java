public class Snippet__4075888 {

    private void finalizeCheckpoint(String checkpointId,
                                        File checkpointedDir,
                                        byte[] txid) throws IOException {
            File[] files = checkpointedDir.listFiles();

            CheckpointMetadata.Builder metadataBuilder = CheckpointMetadata.newBuilder();
            for (File file : files) {
                metadataBuilder.addFiles(file.getName());
            }
            if (null != txid) {
                metadataBuilder.setTxid(UnsafeByteOperations.unsafeWrap(txid));
            }
            metadataBuilder.setCreatedAt(System.currentTimeMillis());

            String destCheckpointPath = RocksUtils.getDestCheckpointMetadataPath(dbPrefix, checkpointId);
            try (OutputStream os = checkpointStore.openOutputStream(destCheckpointPath)) {
                os.write(metadataBuilder.build().toByteArray());
            }
        }

}
