public class DiscardCloseOutputStream extends FilterOutputStream {

  private static final Logger log = LoggerFactory.getLogger(DiscardCloseOutputStream.class);

  public DiscardCloseOutputStream(OutputStream out) {
    super(out);
  }

  /**
   * It is very important to override this method!! The underlying method from FilterOutputStream
   * calls write a single byte at a time and will kill performance.
   */
  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    out.write(b, off, len);
  }

  @Override
  public void close() throws IOException {
    // Discard
    log.trace("Discarded close");
  }

}
