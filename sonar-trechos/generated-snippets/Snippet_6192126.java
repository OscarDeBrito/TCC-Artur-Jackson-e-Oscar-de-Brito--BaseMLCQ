public class Snippet__6192126 {

    @Override
      public void onSelectionChanged(PreviewNode selectedNode) {
        Either<ResourceChange, TextEdit> data = selectedNode.getData();
        if (data != null && data.isLeft()) {
          view.showDiff(null);
          return;
        }

        List<TextEdit> edits = collectTextEditsForSelectedNode(selectedNode);

        updateContentInCompareWidget(selectedNode, edits);
      }

}
