public class ARMQdaddTranslator extends ARMBaseTranslator {
  @Override
  protected void translateCore(final ITranslationEnvironment environment,
      final IInstruction instruction, final List<ReilInstruction> instructions) {
    final IOperandTreeNode registerOperand1 = instruction.getOperands().get(0).getRootNode()
        .getChildren().get(0);
    final IOperandTreeNode registerOperand2 = instruction.getOperands().get(1).getRootNode()
        .getChildren().get(0);
    final IOperandTreeNode registerOperand3 = instruction.getOperands().get(2).getRootNode()
        .getChildren().get(0);

    final String targetRegister = (registerOperand1.getValue());
    final String sourceRegister1 = (registerOperand2.getValue());
    final String sourceRegister2 = (registerOperand3.getValue());

    final OperandSize dw = OperandSize.DWORD;
    final OperandSize bt = OperandSize.BYTE;

    long baseOffset = (instruction.getAddress().toLong() * 0x100) ;

    final String shiftedRn = environment.getNextVariableString();
    final String signedDoesSat1 = environment.getNextVariableString();
    final String signedDoesSat2 = environment.getNextVariableString();
    final String tmpResultVar1 = environment.getNextVariableString();
    final String added2RnRm = environment.getNextVariableString();

    instructions.add(ReilHelpers.createBsh(baseOffset + instructions.size(), dw, sourceRegister2,
        bt, String.valueOf(1), dw, shiftedRn));

    Helpers.signedSat(baseOffset + instructions.size(), environment, instruction, instructions, dw,
        sourceRegister2, dw, sourceRegister2, dw, shiftedRn, "ADD", tmpResultVar1, 32L,
        signedDoesSat1);

    instructions.add(ReilHelpers.createAdd(baseOffset + instructions.size(), dw, tmpResultVar1, dw,
        sourceRegister1, dw, added2RnRm));

    Helpers.signedSat(baseOffset + instructions.size(), environment, instruction, instructions, dw,
        tmpResultVar1, dw, sourceRegister1, dw, added2RnRm, "ADD", targetRegister, 32L,
        signedDoesSat2);

    instructions.add(ReilHelpers.createOr(baseOffset + instructions.size(), bt, signedDoesSat1, bt,
        signedDoesSat2, bt, "Q"));
  }

  /**
   * QDADD{<cond>} <Rd>, <Rm>, <Rn>
   * 
   * Operation:
   * 
   * if ConditionPassed(cond) then Rd = SignedSat(Rm + SignedSat(Rn*2, 32), 32)
   * if SignedDoesSat(Rm + SignedSat(Rn*2, 32), 32) or SignedDoesSat(Rn*2, 32)
   * then Q Flag = 1
   */

  @Override
  public void translate(final ITranslationEnvironment environment, final IInstruction instruction,
      final List<ReilInstruction> instructions) throws InternalTranslationException {
    TranslationHelpers.checkTranslationArguments(environment, instruction, instructions, "QDADD");
    translateAll(environment, instruction, "QDADD", instructions);
  }
}
