public class EnumerableProjectToCalcRule extends RelOptRule {
  /**
   * Creates an EnumerableProjectToCalcRule.
   *
   * @param relBuilderFactory Builder for relational expressions
   */
  public EnumerableProjectToCalcRule(RelBuilderFactory relBuilderFactory) {
    super(operand(EnumerableProject.class, any()), relBuilderFactory, null);
  }

  public void onMatch(RelOptRuleCall call) {
    final EnumerableProject project = call.rel(0);
    final RelNode input = project.getInput();
    final RexProgram program =
        RexProgram.create(input.getRowType(),
            project.getProjects(),
            null,
            project.getRowType(),
            project.getCluster().getRexBuilder());
    final EnumerableCalc calc = EnumerableCalc.create(input, program);
    call.transformTo(calc);
  }
}
