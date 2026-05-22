public class Snippet__6857899 {

    public static String generateSub(final ITranslationEnvironment environment, final long offset,
          final OperandSize size, final String operand1, final String operand2,
          final List<ReilInstruction> instructions) throws IllegalArgumentException {
        Preconditions.checkNotNull(environment, "Error: Argument environment can't be null");
        Preconditions.checkNotNull(size, "Error: Argument size can't be null");
        Preconditions.checkNotNull(operand1, "Error: Argument operand1 can't be null");
        Preconditions.checkNotNull(operand2, "Error: Argument operand2 can't be null");

        final OperandSize resultSize = TranslationHelpers.getNextSize(size);

        final String msbMask = String.valueOf(TranslationHelpers.getMsbMask(size));
        final String shiftMsbLsb = String.valueOf(TranslationHelpers.getShiftMsbLsbMask(size));
        final String carryMask = String.valueOf(getCarryMask(size));
        final String shiftCarryLsb = String.valueOf(-size.getBitSize());
        final String truncateMask = String.valueOf(TranslationHelpers.getAllBitsMask(size));

        final String maskedOp1 = environment.getNextVariableString();
        final String maskedOp2 = environment.getNextVariableString();
        final String subResult = environment.getNextVariableString();
        final String msbResult = environment.getNextVariableString();
        final String msbSameBefore = environment.getNextVariableString();
        final String msbHasChanged = environment.getNextVariableString();
        final String tempOf = environment.getNextVariableString();
        final String tempCf = environment.getNextVariableString();
        final String truncatedResult = environment.getNextVariableString();

        // Isolate the MSBs of the two operands
        instructions.add(ReilHelpers.createAnd(offset, size, operand1, size, msbMask, size, maskedOp1));
        instructions.add(ReilHelpers.createAnd(offset + 1, size, operand2, size, msbMask, size,
            maskedOp2));

        // Perform the subtraction
        instructions.add(ReilHelpers.createSub(offset + 2, size, operand1, size, operand2, resultSize,
            subResult));

        // Isolate the MSB of the result and put it into the Sign Flag
        instructions.add(ReilHelpers.createAnd(offset + 3, resultSize, subResult, resultSize, msbMask,
            size, msbResult));
        instructions.add(ReilHelpers.createBsh(offset + 4, size, msbResult, size, shiftMsbLsb,
            OperandSize.BYTE, SIGN_FLAG));

        // Find out if the MSB of the two operands were different and whether the MSB of the first
        // operand changed
        instructions.add(ReilHelpers.createXor(offset + 5, size, maskedOp1, size, maskedOp2, size,
            msbSameBefore));
        instructions.add(ReilHelpers.createXor(offset + 6, size, maskedOp1, size, msbResult, size,
            msbHasChanged));
        instructions.add(ReilHelpers.createAnd(offset + 7, size, msbSameBefore, size, msbHasChanged,
            size, tempOf));

        // Write the result into the Overflow Flag
        instructions.add(ReilHelpers.createBsh(offset + 8, size, tempOf, size, shiftMsbLsb,
            OperandSize.BYTE, OVERFLOW_FLAG));

        // Update the Carry Flag
        instructions.add(ReilHelpers.createAnd(offset + 9, resultSize, subResult, resultSize,
            carryMask, resultSize, tempCf));
        instructions.add(ReilHelpers.createBsh(offset + 10, resultSize, tempCf, resultSize,
            shiftCarryLsb, OperandSize.BYTE, CARRY_FLAG));

        // Truncate the result to fit into the target
        instructions.add(ReilHelpers.createAnd(offset + 11, resultSize, subResult, resultSize,
            truncateMask, size, truncatedResult));

        // Update the Zero Flag
        instructions.add(ReilHelpers.createBisz(offset + 12, size, truncatedResult, OperandSize.BYTE,
            ZERO_FLAG));

        return truncatedResult;
      }

}
