public class Snippet__5843392 {

    public Snippet__5843392(java.io.InputStream dstream, String encoding, int startline,
      int startcolumn, int buffersize) throws java.io.UnsupportedEncodingException
      {
        this(encoding == null ? new java.io.InputStreamReader(dstream) : new java.io.InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
      }

}
