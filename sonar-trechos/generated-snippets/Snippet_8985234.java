class LongStreamIfFunction extends AbstractLongValueStream {
  private final BooleanValue ifExpr;
  private final LongValueStream thenExpr;
  private final LongValueStream elseExpr;
  public static final String name = IfFunction.name;
  private final String exprStr;
  private final ExpressionType funcType;
  
  public LongStreamIfFunction(BooleanValue ifExpr, LongValueStream thenExpr, LongValueStream elseExpr) throws SolrException {
    this.ifExpr = ifExpr;
    this.thenExpr = thenExpr;
    this.elseExpr = elseExpr;
    this.exprStr = AnalyticsValueStream.createExpressionString(name,ifExpr,thenExpr,elseExpr);
    this.funcType = AnalyticsValueStream.determineMappingPhase(exprStr,ifExpr,thenExpr,elseExpr);
  }

  @Override
  public void streamLongs(LongConsumer cons) {
    boolean ifValue = ifExpr.getBoolean();
    if (ifExpr.exists()) {
      if (ifValue) {
        thenExpr.streamLongs(cons);
      }
      else {
        elseExpr.streamLongs(cons);
      }
    }
  }

  @Override
  public String getName() {
    return name;
  }
  @Override
  public String getExpressionStr() {
    return exprStr;
  }
  @Override
  public ExpressionType getExpressionType() {
    return funcType;
  }
}
