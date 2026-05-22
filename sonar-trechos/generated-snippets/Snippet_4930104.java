public class Snippet__4930104 {

    private static void renderChildren(List<Tree> children, int indent,
            boolean parentLabelNull, StringBuilder sb) {
          boolean firstSibling = true;
          boolean leftSibIsPreTerm = true; // counts as true at beginning
          for (Tree child : children) {
            renderTree(child, indent, parentLabelNull, firstSibling, leftSibIsPreTerm, false, sb);
            leftSibIsPreTerm = child.isPreTerminal();
            firstSibling = false;
          }
        }

}
