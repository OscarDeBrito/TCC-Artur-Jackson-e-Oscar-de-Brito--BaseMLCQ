public class OffsetCompiler {
    private static final ParseNodeFactory NODE_FACTORY = new ParseNodeFactory();

    public static final PDatum OFFSET_DATUM = new PDatum() {
        @Override
        public boolean isNullable() {
            return false;
        }

        @Override
        public PDataType getDataType() {
            return PInteger.INSTANCE;
        }

        @Override
        public Integer getMaxLength() {
            return null;
        }

        @Override
        public Integer getScale() {
            return null;
        }

        @Override
        public SortOrder getSortOrder() {
            return SortOrder.getDefault();
        }
    };

    private OffsetCompiler() {}

    public static Integer compile(StatementContext context, FilterableStatement statement) throws SQLException {
        OffsetNode offsetNode = statement.getOffset();
        if (offsetNode == null) { return null; }
        OffsetParseNodeVisitor visitor = new OffsetParseNodeVisitor(context);
        offsetNode.getOffsetParseNode().accept(visitor);
        return visitor.getOffset();
    }

    private static class OffsetParseNodeVisitor extends TraverseNoParseNodeVisitor<Void> {
        private final StatementContext context;
        private Integer offset;

        public OffsetParseNodeVisitor(StatementContext context) {
            this.context = context;
        }

        public Integer getOffset() {
            return offset;
        }

        @Override
        public Void visit(LiteralParseNode node) throws SQLException {
            Object offsetValue = node.getValue();
            if (offsetValue != null) {
                Integer offset = (Integer)OFFSET_DATUM.getDataType().toObject(offsetValue, node.getType());
                if (offset.intValue() >= 0) {
                    this.offset = offset;
                }
            }
            return null;
        }

        @Override
        public Void visit(BindParseNode node) throws SQLException {
            // This is for static evaluation in SubselectRewriter.
            if (context == null) return null;

            Object value = context.getBindManager().getBindValue(node);
            context.getBindManager().addParamMetaData(node, OFFSET_DATUM);
            // Resolve the bind value, create a LiteralParseNode, and call the
            // visit method for it.
            // In this way, we can deal with just having a literal on one side
            // of the expression.
            visit(NODE_FACTORY.literal(value, OFFSET_DATUM.getDataType()));
            return null;
        }

    }

}
