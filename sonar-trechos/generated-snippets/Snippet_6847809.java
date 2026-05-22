public class StfdTranslator implements IInstructionTranslator {

  @Override
  public void translate(final ITranslationEnvironment environment, final IInstruction instruction,
      final List<ReilInstruction> instructions) throws InternalTranslationException {

    UnknownGenerator.generate(environment, instruction, instructions,
        "stfd");
  }
}
