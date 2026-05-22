public class PlusExpression implements MemoryExpressionElement {
  /**
   * The operands of the plus expression.
   */
  private final List<MemoryExpressionElement> children;

  /**
   * Creates a new expression object.
   *
   * @param children The operands of the plus expression.
   */
  public PlusExpression(final List<MemoryExpressionElement> children) {
    this.children = children;
  }

  /**
   * Returns the children of the expression.
   *
   * @return The children of the expression.
   */
  public List<MemoryExpressionElement> getChildren() {
    return new ArrayList<>(children);
  }

  @Override
  public String toString() {
    final StringBuilder ret = new StringBuilder();
    for (final MemoryExpressionElement child : children) {
      ret.append(child.toString());
      if (child != Iterables.getLast(children)) {
        ret.append('+');
      }
    }
    return ret.toString();
  }

  @Override
  public void visit(final MemoryExpressionVisitor visitor) {
    visitor.visit(this);
  }

}
