public class XmlUtils {

  public static boolean isValidXml(String input) {
    boolean result = true;
    try {
      if (StringUtils.isBlank(input)) {
        result = false;
      } else {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // skip dtd references
        dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        dBuilder.parse(new ByteArrayInputStream(input.getBytes("UTF-8")));
      }
    } catch (Exception e) {
      result = false;
    }
    return result;
  }

}
