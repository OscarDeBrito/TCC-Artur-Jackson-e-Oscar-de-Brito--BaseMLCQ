public class ASTEnclosingObject extends SimpleNode {

    public ASTEnclosingObject(Expression expression) {
        this();
        Node node = wrapChild(expression);
        jjtAddChild(node, 0);
        node.jjtSetParent(this);
    }

    public ASTEnclosingObject() {
        this(0);
    }

    protected ASTEnclosingObject(int i) {
        super(i);
    }

    @Override
    protected String getExpressionOperator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object evaluateNode(Object o) throws Exception {
        return o;
    }

    @Override
    public void appendAsString(Appendable out) throws IOException {
        out.append("SUPER:");
        super.appendAsString(out);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTEnclosingObject(id);
    }

    @Override
    public int getType() {
        return Expression.ENCLOSING_OBJECT;
    }
}
