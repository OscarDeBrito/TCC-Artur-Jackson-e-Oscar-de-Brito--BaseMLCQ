public class IntIntTextVertexValueReader extends
      TextVertexValueReaderFromEachLineProcessed<IntPair> {

    @Override
    protected IntPair preprocessLine(Text line) throws IOException {
      String[] tokens = SEPARATOR.split(line.toString());
      return new IntPair(Integer.parseInt(tokens[0]),
          Integer.parseInt(tokens[1]));
    }

    @Override
    protected IntWritable getId(IntPair data) throws IOException {
      return new IntWritable(data.getFirst());
    }

    @Override
    protected IntWritable getValue(IntPair data) throws IOException {
      return new IntWritable(data.getSecond());
    }
  }
