public final class CNameHelp implements IHelpInformation {
  @Override
  public String getText() {
    return "In this field you can edit the name of the tag.";
  }

  @Override
  public URL getUrl() {
    return CHelpFunctions.urlify(CHelpFunctions.MAIN_WINDOW_FILE);
  }
}
