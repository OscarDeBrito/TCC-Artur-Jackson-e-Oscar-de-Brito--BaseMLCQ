public class Snippet__5752009 {

    public void visit(SQLBuilderContext context, LogicalNode node, Stack<LogicalNode> stack) {
        stack.push(node);

        switch (node.getType()) {
        case SCAN:
          visitScan(context, (ScanNode) node, stack);
          break;

        case GROUP_BY:
          visitGroupBy(context, (GroupbyNode) node, stack);
          break;

        case SELECTION:
          visitFilter(context, (SelectionNode) node, stack);
          break;

        case PROJECTION:
          visitProjection(context, (ProjectionNode) node, stack);
          break;

        case TABLE_SUBQUERY:
          visitDerivedSubquery(context, (TableSubQueryNode) node, stack);
          break;

        default:
          throw new TajoRuntimeException(new UnsupportedException("plan node '" + node.getType().name() + "'"));
        }

        stack.pop();
      }

}
