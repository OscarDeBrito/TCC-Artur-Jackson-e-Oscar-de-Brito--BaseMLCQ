public class Snippet__4910241 {

    private long numChildNodes(Node node) throws RepositoryException {
                NodeIterator iterator = node.getNodes();
                if (iterator.getSize() >= 0) {
                    return iterator.getSize();
                } else {
                    int num = 0;
                    while (iterator.hasNext() && num < BUCKET_SIZE) {
                        iterator.nextNode();
                        num++;
                    }
                    return num;
                }
            }

}
