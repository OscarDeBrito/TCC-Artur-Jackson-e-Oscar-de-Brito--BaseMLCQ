final class FileIterator implements BytesRefIterator {
    private boolean done = false;
    private final BytesRefBuilder spare = new BytesRefBuilder();
    @Override
    public BytesRef next() throws IOException {
      if (done) {
        return null;
      }
      boolean success = false;
      BytesRef result;
      try {
        String line;
        if ((line = in.readLine()) != null) {
          spare.copyChars(line);
          result = spare.get();
        } else {
          done = true;
          IOUtils.close(in);
          result = null;
        }
        success = true;
      } finally {
        if (!success) {
          IOUtils.closeWhileHandlingException(in);
        }
      }
      return result;
    }
  }
