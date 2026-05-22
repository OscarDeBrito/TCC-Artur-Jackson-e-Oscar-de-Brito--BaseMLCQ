public class Snippet__9471363 {

    public Snippet__9471363(URI uri, HttpRequestBase req,
          HttpResponse resp) throws IOException {
        this.uri = uri;
        this.req = req;
        this.resp = resp;
        constructionStack = LOG.isDebugEnabled() ? new Exception("stack") : null;
        if (req == null) {
          throw new IllegalArgumentException("Null 'request' parameter ");
        }
        try {
          inStream = resp.getEntity().getContent();
        } catch (IOException e) {
          inStream = new ByteArrayInputStream(new byte[]{});
          throw releaseAndRethrow("getResponseBodyAsStream() in constructor -" + e, e);
        }
      }

}
