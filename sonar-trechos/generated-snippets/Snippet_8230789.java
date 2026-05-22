public class Snippet__8230789 {

    protected void addAncestorPath(TraversableSource source, Stack ancestors)
        throws SAXException, ProcessingException {
            if (ancestors.empty()) {
                this.isRequestedSource = true;
                addPath(source, depth);
            } else {
                startNode(COL_NODE_NAME, (TraversableSource) ancestors.pop());
                addAncestorPath(source, ancestors);
                endNode(COL_NODE_NAME);
            }
        }

}
