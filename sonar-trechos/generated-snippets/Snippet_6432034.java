public class Snippet__6432034 {

    @Override
        public PropertyNode getMaxNodesPerHistoryUpdateDataNode() {
            Optional<VariableNode> propertyNode = getPropertyNode(OperationLimitsType.MAX_NODES_PER_HISTORY_UPDATE_DATA);
            return (PropertyNode) propertyNode.orElse(null);
        }

}
