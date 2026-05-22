public class Snippet__4737230 {

    public static Schema readSchemaFromLogFile(FileSystem fs, Path path) throws IOException {
        Reader reader = HoodieLogFormat.newReader(fs, new HoodieLogFile(path), null);
        HoodieAvroDataBlock lastBlock = null;
        while (reader.hasNext()) {
          HoodieLogBlock block = reader.next();
          if (block instanceof HoodieAvroDataBlock) {
            lastBlock = (HoodieAvroDataBlock) block;
          }
        }
        reader.close();
        if (lastBlock != null) {
          return lastBlock.getSchema();
        }
        return null;
      }

}
