public class GiraphTextInputFormat
    extends GiraphFileInputFormat<LongWritable, Text> {
  @Override
  public RecordReader<LongWritable, Text>
  createRecordReader(InputSplit split, TaskAttemptContext context) {
    return new LineRecordReader();
  }

  @Override
  protected boolean isSplitable(JobContext context, Path file) {
    CompressionCodec codec =
        new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
    return codec == null;
  }
}
