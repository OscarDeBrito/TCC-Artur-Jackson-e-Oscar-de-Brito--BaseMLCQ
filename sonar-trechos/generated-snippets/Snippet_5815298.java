public final class GraphSONInputFormat extends FileInputFormat<NullWritable, VertexWritable> implements HadoopPoolsConfigurable {

    @Override
    public RecordReader<NullWritable, VertexWritable> createRecordReader(final InputSplit split, final TaskAttemptContext context) throws IOException, InterruptedException {
        RecordReader<NullWritable, VertexWritable> reader = new GraphSONRecordReader();
        reader.initialize(split, context);
        return reader;
    }

    @Override
    protected boolean isSplitable(final JobContext context, final Path file) {
        return null == new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
    }
}
