public class Snippet__6174003 {

    private String resolve(String template) throws TemplateNotFoundException {
        try (Reader reader = new InputStreamReader(IoUtil.getResource(template))) {
          return CharStreams.toString(reader);
        } catch (IOException e) {
          throw new TemplateNotFoundException(e.getMessage(), e);
        }
      }

}
