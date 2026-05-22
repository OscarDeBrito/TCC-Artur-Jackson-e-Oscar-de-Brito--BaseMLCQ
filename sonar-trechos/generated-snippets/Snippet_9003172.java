public class PayloadSpanCollector implements SpanCollector {

  private final Collection<byte[]> payloads = new ArrayList<>();

  @Override
  public void collectLeaf(PostingsEnum postings, int position, Term term) throws IOException {
    BytesRef payload = postings.getPayload();
    if (payload == null)
      return;
    final byte[] bytes = new byte[payload.length];
    System.arraycopy(payload.bytes, payload.offset, bytes, 0, payload.length);
    payloads.add(bytes);
  }

  @Override
  public void reset() {
    payloads.clear();
  }

  /**
   * @return the collected payloads
   */
  public Collection<byte[]> getPayloads() {
    return payloads;
  }
}
