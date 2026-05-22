public class Snippet__7107337 {

    private boolean namedNodeMapsEqual(NamedNodeMap a, NamedNodeMap b) {
            if (a.getLength() != b.getLength()) {
                return false;
            }
            for (int i = 0; i < a.getLength(); i++) {
                Node aNode = a.item(i);
                Node bNode = aNode.getLocalName() == null
                        ? b.getNamedItem(aNode.getNodeName())
                        : b.getNamedItemNS(aNode.getNamespaceURI(), aNode.getLocalName());
                if (bNode == null || !aNode.isEqualNode(bNode)) {
                    return false;
                }
            }
            return true;
        }

}
