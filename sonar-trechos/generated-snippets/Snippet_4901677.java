public class Snippet__4901677 {

    private Path getNameElement(
                NodeId nodeId, NodeId parentId, HierarchyManager hmgr)
                throws ItemStateException {
            try {
                Name name = hmgr.getName(nodeId, parentId);
                return PathFactoryImpl.getInstance().create(name);
            } catch (RepositoryException e) {
                String msg = "Unable to get name for node with id: " + nodeId;
                throw new ItemStateException(msg, e);
            }
        }

}
