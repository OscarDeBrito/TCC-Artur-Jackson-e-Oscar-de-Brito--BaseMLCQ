@Visitable
public abstract class Statement extends Node implements HasSourcePosition, Cloneable<Statement> {
  // unknown by default.
  private SourcePosition sourcePosition;

  public Statement(SourcePosition sourcePosition) {
    setSourcePosition(sourcePosition);
  }

  @Override
  public SourcePosition getSourcePosition() {
    return sourcePosition;
  }

  public void setSourcePosition(SourcePosition sourcePosition) {
    this.sourcePosition = checkNotNull(sourcePosition);
  }

  @Override
  public abstract Statement clone();

  @Override
  public Node accept(Processor processor) {
    return Visitor_Statement.visit(processor, this);
  }
}
