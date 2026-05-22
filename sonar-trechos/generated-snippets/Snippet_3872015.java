public class Snippet__3872015 {

    protected String getHash(T data, String salt) {
        String json = null;
        try {
          json = MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
          throw new RuntimeException("Error during mapping message to calculate hash", e);
        }
        String generatedPassword = null;
        try {
          MessageDigest md = MessageDigest.getInstance("SHA-512");
          md.update(salt.getBytes("UTF-8"));
          byte[] bytes = md.digest(json.getBytes("UTF-8"));
          StringBuilder sb = new StringBuilder();
          for (byte b : bytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
          }
          generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
          e.printStackTrace();
        }
        return generatedPassword;
      }

}
