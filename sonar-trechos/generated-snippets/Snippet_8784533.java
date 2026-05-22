static class WritableSerializer extends Configured implements
  	Serializer<Writable> {
    
    private DataOutputStream dataOut;
    
    @Override
    public void open(OutputStream out) {
      if (out instanceof DataOutputStream) {
        dataOut = (DataOutputStream) out;
      } else {
        dataOut = new DataOutputStream(out);
      }
    }

    @Override
    public void serialize(Writable w) throws IOException {
      w.write(dataOut);
    }

    @Override
    public void close() throws IOException {
      dataOut.close();
    }

  }
