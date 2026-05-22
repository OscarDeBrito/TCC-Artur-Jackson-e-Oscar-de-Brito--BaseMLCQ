public class SamzaSqlUdfOperatorTable implements SqlOperatorTable {

  private final ListSqlOperatorTable operatorTable;

  public SamzaSqlUdfOperatorTable(List<SamzaSqlScalarFunctionImpl> scalarFunctions) {
    operatorTable = new ListSqlOperatorTable(getSqlOperators(scalarFunctions));
  }

  private List<SqlOperator> getSqlOperators(List<SamzaSqlScalarFunctionImpl> scalarFunctions) {
    return scalarFunctions.stream().map(this::getSqlOperator).collect(Collectors.toList());
  }

  private SqlOperator getSqlOperator(SamzaSqlScalarFunctionImpl scalarFunction) {
    int numArguments = scalarFunction.numberOfArguments();
    UdfMetadata udfMetadata = scalarFunction.getUdfMetadata();

    if(udfMetadata.isDisableArgCheck()) {
      return new SqlUserDefinedFunction(new SqlIdentifier(scalarFunction.getUdfName(), SqlParserPos.ZERO),
          o -> scalarFunction.getReturnType(o.getTypeFactory()), null, Checker.ANY_CHECKER,
          null, scalarFunction);
    } else {
      return new SqlUserDefinedFunction(new SqlIdentifier(scalarFunction.getUdfName(), SqlParserPos.ZERO),
          o -> scalarFunction.getReturnType(o.getTypeFactory()), null, Checker.getChecker(numArguments, numArguments),
          null, scalarFunction);
    }
  }

  @Override
  public void lookupOperatorOverloads(SqlIdentifier opName, SqlFunctionCategory category, SqlSyntax syntax,
      List<SqlOperator> operatorList) {
    operatorTable.lookupOperatorOverloads(opName, category, syntax, operatorList);
  }

  @Override
  public List<SqlOperator> getOperatorList() {
    return operatorTable.getOperatorList();
  }
}
