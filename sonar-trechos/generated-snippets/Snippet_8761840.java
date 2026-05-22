public static class WordMedianMapper extends
      Mapper<Object, Text, IntWritable, IntWritable> {

    private IntWritable length = new IntWritable();

    /**
     * Emits a key-value pair for counting the word. Outputs are (IntWritable,
     * IntWritable).
     * 
     * @param value
     *          This will be a line of text coming in from our input file.
     */
    public void map(Object key, Text value, Context context)
        throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        String string = itr.nextToken();
        length.set(string.length());
        context.write(length, ONE);
      }
    }
  }
