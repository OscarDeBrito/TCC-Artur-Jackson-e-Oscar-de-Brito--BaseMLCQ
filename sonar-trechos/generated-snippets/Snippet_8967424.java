public class Snippet__8967424 {

    public Text evaluate(String xml, String path) {
        String s = xpath.evalString(xml, path);
        if (s == null) {
          return null;
        }

        result.set(s);
        return result;
      }

}
