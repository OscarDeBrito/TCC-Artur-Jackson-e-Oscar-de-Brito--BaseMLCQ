public final class IfElseNode extends AbstractBlockCommandNode implements ConditionalBlockNode {

  /**
   * @param id The id for this node.
   * @param sourceLocation The node's source location.
   */
  public IfElseNode(int id, SourceLocation sourceLocation) {
    super(id, sourceLocation, "else");
  }

  /**
   * Copy constructor.
   *
   * @param orig The node to copy.
   */
  private IfElseNode(IfElseNode orig, CopyState copyState) {
    super(orig, copyState);
  }

  @Override
  public Kind getKind() {
    return Kind.IF_ELSE_NODE;
  }

  @Override
  public String toSourceString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getTagString());
    appendSourceStringForChildren(sb);
    // Note: No end tag.
    return sb.toString();
  }

  @Override
  public IfElseNode copy(CopyState copyState) {
    return new IfElseNode(this, copyState);
  }
}
