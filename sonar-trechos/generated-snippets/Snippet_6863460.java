public class Snippet__6863460 {

    private void colorLoops() {
        try {
          for (final INaviViewNode currentNode : getGraph().getNodes()) {
            if (currentNode.getParents().isEmpty()) {
              CLoopHighlighter.colorLoops(getGraph(), currentNode);
              break;
            }
          }
        } catch (final MalformedGraphException exception) {
          NaviLogger.warning("Error: Graph is malformed, can not color loops");
        }
      }

}
