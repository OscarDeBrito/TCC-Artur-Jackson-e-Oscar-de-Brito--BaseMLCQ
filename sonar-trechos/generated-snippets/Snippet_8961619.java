public class Snippet__8961619 {

    @Override
      public String serialize(EventMessage message) {
        String messageAsString = MessageSerializer.super.serialize(message);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
          GZIPOutputStream gout = new GZIPOutputStream(baos);
          gout.write(messageAsString.getBytes(StandardCharsets.UTF_8));
          gout.close();
          byte[] compressed = baos.toByteArray();
          return new String(Base64.getEncoder().encode(compressed), StandardCharsets.UTF_8);
        } catch (IOException e) {
          LOG.error("could not use gzip output stream", e);
          LOG.debug("message " + messageAsString);
          throw new RuntimeException("could not use the gzip output Stream", e);
        }
      }

}
