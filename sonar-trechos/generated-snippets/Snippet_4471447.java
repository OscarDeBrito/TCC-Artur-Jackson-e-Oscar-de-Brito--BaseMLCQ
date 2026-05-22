public class DrillCalciteSqlBetweenOperatorWrapper extends SqlBetweenOperator implements DrillCalciteSqlWrapper  {
  private final SqlBetweenOperator operator;

  public DrillCalciteSqlBetweenOperatorWrapper(SqlBetweenOperator sqlBetweenOperator) {
    super(sqlBetweenOperator.flag, sqlBetweenOperator.isNegated());
    operator = sqlBetweenOperator;
  }

  @Override
  public SqlOperator getOperator() {
    return operator;
  }

  /**
   * Since Calcite has its rule for type compatibility
   * (see {@link org.apache.calcite.sql.type.SqlTypeUtil#isComparable(org.apache.calcite.rel.type.RelDataType,
   * org.apache.calcite.rel.type.RelDataType)}), which is usually different from Drill's, this method is overridden here to avoid
   * Calcite early terminating the queries.
   */
  @Override
  public boolean checkOperandTypes(SqlCallBinding callBinding, boolean throwOnFailure) {
    final List<TypeProtos.MinorType> types = new ArrayList<>();
    for (int i = 0; i < callBinding.getOperandCount(); i++) {
      final TypeProtos.MinorType inMinorType = TypeInferenceUtils.getDrillTypeFromCalciteType(callBinding.getOperandType(i));
      if (inMinorType == TypeProtos.MinorType.LATE) {
        return true;
      }
      types.add(inMinorType);
    }

    final boolean isCompatible = TypeCastRules.getLeastRestrictiveType(types) != null;
    if (!isCompatible && throwOnFailure) {
      throw callBinding.newValidationSignatureError();
    }
    return isCompatible;
  }
}
