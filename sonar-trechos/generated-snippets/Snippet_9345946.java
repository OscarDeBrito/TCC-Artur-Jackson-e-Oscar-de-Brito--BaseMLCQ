public class Snippet__9345946 {

    public void selectAllAccessibleSelection() {
                   TreeModel model = JTree.this.getModel();
                   if (model != null) {
                       int childCount = getAccessibleChildrenCount();
                       TreePath path;
                       for (int i = 0; i < childCount; i++) {
                           path = getChildTreePath(i);
                           JTree.this.addSelectionPath(path);
                       }
                    }
                }

}
