public class TypeScriptLanguageDescriptionProvider implements Provider<LanguageDescription> {

  private static final String[] EXTENSIONS = new String[] {Constants.TS_EXT};
  private static final String MIME_TYPE = Constants.TS_MIME_TYPE;

  @Override
  public LanguageDescription get() {
    LanguageDescription description = new LanguageDescription();
    description.setFileExtensions(asList(EXTENSIONS));
    description.setLanguageId(Constants.TS_LANG);
    description.setMimeType(MIME_TYPE);
    description.setHighlightingConfiguration(
        "[\n"
            + "  {\"include\":\"orion.js\"},\n"
            + "  {\"match\":\"\\\\b(?:constructor|declare|module)\\\\b\",\"name\" :\"keyword.operator.typescript\"},\n"
            + "  {\"match\":\"\\\\b(?:any|boolean|number|string)\\\\b\",\"name\" : \"storage.type.typescript\"}\n"
            + "]");

    return description;
  }
}
