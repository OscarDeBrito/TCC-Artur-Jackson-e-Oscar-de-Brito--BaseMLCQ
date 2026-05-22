public abstract class TerminalOperator<T extends OperatorDesc> extends
    Operator<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  /** Kryo ctor. */
  protected TerminalOperator() {
    super();
  }

  public TerminalOperator(CompilationOpContext ctx) {
    super(ctx);
  }

  @Override
  public String getName() {
    return getOperatorName();
  }

  static public String getOperatorName() {
    return "END";
  }

}
