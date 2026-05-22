public class NorTranslator implements IInstructionTranslator {

  @Override
  public void translate(final ITranslationEnvironment environment, final IInstruction instruction,
      final List<ReilInstruction> instructions) throws InternalTranslationException {
    TranslationHelpers.checkTranslationArguments(environment, instruction, instructions, "nor");

    final Triple<IOperandTree, IOperandTree, IOperandTree> operands =
        OperandLoader.loadDuplicateFirst(instruction);
    final String targetRegister = operands.first().getRootNode().getChildren().get(0).getValue();
    final String sourceRegister1 = operands.second().getRootNode().getChildren().get(0).getValue();
    final String sourceRegister2 = operands.third().getRootNode().getChildren().get(0).getValue();

    final OperandSize dw = OperandSize.DWORD;

    final long baseOffset = ReilHelpers.toReilAddress(instruction.getAddress()).toLong();
    long offset = baseOffset;

    final String temporaryOrResult = environment.getNextVariableString();

    instructions.add(ReilHelpers.createOr(offset++, dw, sourceRegister1, dw, sourceRegister2, dw,
        temporaryOrResult));
    instructions.add(ReilHelpers.createXor(offset, dw, temporaryOrResult, dw,
        String.valueOf(0xFFFFFFFFL), dw, targetRegister));
  }
}
