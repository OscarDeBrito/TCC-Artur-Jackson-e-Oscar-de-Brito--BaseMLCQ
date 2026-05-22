public static class FilterRecordWriter<K,V> implements RecordWriter<K,V> {

    protected RecordWriter<K,V> rawWriter = null;

    public FilterRecordWriter() throws IOException {
      rawWriter = null;
    }

    public FilterRecordWriter(RecordWriter<K,V> rawWriter)  throws IOException {
      this.rawWriter = rawWriter;
    }

    public void close(Reporter reporter) throws IOException {
      getRawWriter().close(reporter);
    }

    public void write(K key, V value) throws IOException {
      getRawWriter().write(key, value);
    }
    
    private RecordWriter<K,V> getRawWriter() throws IOException {
      if (rawWriter == null) {
        throw new IOException ("Record Writer not set for FilterRecordWriter");
      }
      return rawWriter;
    }
  }
