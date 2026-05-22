public class GlobalMetadataJsonMerger implements MetadataMerger<String> {
  private GlobalMetadata mergedMetadata;

  public GlobalMetadataJsonMerger() {
    mergedMetadata = new GlobalMetadata();
  }

  @Override
  public void update(String metadata) {
    try {
      GlobalMetadata parsedMetadata = GlobalMetadata.fromJson(metadata);
      mergedMetadata.addAll(parsedMetadata);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error parsing metadata", e);
    }
  }

  @Override
  public void update(FsWriterMetrics metrics) {
    long numRecords = mergedMetadata.getNumRecords();
    int numFiles = mergedMetadata.getNumFiles();

    for (FsWriterMetrics.FileInfo fileInfo: metrics.getFileInfos()) {
      numRecords += fileInfo.getNumRecords();
      numFiles += 1;

      mergedMetadata.setFileMetadata(fileInfo.getFileName(), GlobalMetadata.NUM_RECORDS_KEY,
          Long.valueOf(fileInfo.getNumRecords()));
    }

    mergedMetadata.setNumRecords(numRecords);
    mergedMetadata.setNumOutputFiles(numFiles);
  }

  @Override
  public String getMergedMetadata() {
    try {
      return mergedMetadata.toJson();
    } catch (IOException e) {
      throw new AssertionError("Unexpected IOException serializing to JSON", e);
    }
  }
}
