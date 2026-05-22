public final class SwitchStmt extends LabeledStatement {
  private static final long serialVersionUID = -7284973291082281855L;

  @ReflectiveCtor
  public SwitchStmt(
      FilePosition pos, String label, List<? extends ParseTreeNode> children) {
    super(pos, label, ParseTreeNode.class);
    createMutation().appendChildren(children).execute();
  }

  public SwitchStmt(
      FilePosition pos, String label,
      Expression valueExpr, List<SwitchCase> cases) {
    super(pos, label, ParseTreeNode.class);
    createMutation().appendChild(valueExpr).appendChildren(cases).execute();
  }

  @Override
  protected void childrenChanged() {
    super.childrenChanged();
    List<? extends ParseTreeNode> children = children();
    ParseTreeNode valueExpr = children.get(0);
    if (!(valueExpr instanceof Expression)) {
      throw new ClassCastException(
          "Expected " + Expression.class.getName() + " not "
          + valueExpr.getClass().getName());
    }
    for (ParseTreeNode node : children.subList(1, children.size())) {
      if (!(node instanceof SwitchCase)) {
        throw new ClassCastException(
            "Expected " + SwitchCase.class.getName() + " not "
            + (node != null ? node.getClass().getName() : "<null>"));
      }
    }
  }

  @Override
  public void continues(Map<String, List<ContinueStmt>> contsReaching) {
    // switch statements don't intercept continues
    for (ParseTreeNode child : children()) {
      if (child instanceof Statement) {
        ((Statement) child).continues(contsReaching);
      }
    }
  }

  @Override
  public boolean isTargetForContinue() { return false; }

  public void render(RenderContext rc) {
    TokenConsumer out = rc.getOut();
    out.mark(getFilePosition());
    String label = getRenderedLabel();
    if (null != label) {
      out.consume(label);
      out.consume(":");
    }
    Iterator<? extends ParseTreeNode> it = children().iterator();
    out.consume("switch");
    out.consume("(");
    it.next().render(rc);
    out.consume(")");
    out.consume("{");
    while (it.hasNext()) {
      SwitchCase caseStmt = (SwitchCase) it.next();
      caseStmt.render(rc);
    }
    out.mark(FilePosition.endOfOrNull(getFilePosition()));
    out.consume("}");
  }

  @Override
  public boolean isTerminal() {
    return true;
  }

  public boolean hasHangingConditional() { return false; }
}
