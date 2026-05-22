public class ExtTranslator implements IInstructionTranslator {
  @Override
  public void translate(final ITranslationEnvironment environment, final IInstruction instruction,
      final List<ReilInstruction> instructions) throws InternalTranslationException {
    TranslationHelpers.checkTranslationArguments(environment, instruction, instructions, "ext");

    final long baseOffset = ReilHelpers.toReilAddress(instruction.getAddress()).toLong();
    instructions.add(ReilHelpers.createUnknown(baseOffset));
    // TODO final String todo = "";
  }
}
