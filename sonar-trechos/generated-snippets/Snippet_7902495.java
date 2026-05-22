public class Snippet__7902495 {

    private ReferenceCountedWriter createReferenceCountedWriter(Path logFilePath) {
        try {
          Files.createDirectories(logFilePath.getParent());
          return new ReferenceCountedWriter(
              new OutputStreamWriter(
                  new FileOutputStream(logFilePath.toString()), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
          throw new RuntimeException(String.format("Could not create file [%s].", logFilePath), e);
        } catch (IOException e) {
          throw new RuntimeException(String.format("Exception wrapping file [%s].", logFilePath), e);
        }
      }

}
