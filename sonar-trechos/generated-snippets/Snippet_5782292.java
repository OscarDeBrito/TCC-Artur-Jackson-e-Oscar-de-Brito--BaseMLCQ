public class Snippet__5782292 {

    public static void verifyReply(String base64Hash, String msg, SecretKey key) throws IOException {
        byte[] hash = Base64.decodeBase64(base64Hash.getBytes(Charsets.UTF_8));
        boolean res = verifyHash(hash, msg.getBytes(Charsets.UTF_8), key);

        if(res != true) {
          throw new IOException("Verification of the hashReply failed");
        }
      }

}
