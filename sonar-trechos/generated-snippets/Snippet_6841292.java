public class Snippet__6841292 {

    public static boolean match(
          String patternText,
          ParseTreeNode specimen,
          Map<String, ParseTreeNode> bindings) {
        Map<String, ParseTreeNode> tempBindings = getPatternNode(patternText)
            .match(specimen);

        if (tempBindings != null) {
          bindings.putAll(tempBindings);
          return true;
        }
        return false;
      }

}
