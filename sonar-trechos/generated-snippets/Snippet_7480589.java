public final class AArch64FloatConvertOp extends AArch64LIRInstruction {
    private static final LIRInstructionClass<AArch64FloatConvertOp> TYPE = LIRInstructionClass.create(AArch64FloatConvertOp.class);

    private final FloatConvert op;
    @Def protected AllocatableValue resultValue;
    @Use protected AllocatableValue inputValue;

    protected AArch64FloatConvertOp(FloatConvert op, AllocatableValue resultValue, AllocatableValue inputValue) {
        super(TYPE);
        this.op = op;
        this.resultValue = resultValue;
        this.inputValue = inputValue;
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AArch64MacroAssembler masm) {
        int fromSize = inputValue.getPlatformKind().getSizeInBytes() * Byte.SIZE;
        int toSize = resultValue.getPlatformKind().getSizeInBytes() * Byte.SIZE;

        Register result = asRegister(resultValue);
        Register input = asRegister(inputValue);
        switch (op) {
            case F2I:
            case D2I:
            case F2L:
            case D2L:
                masm.fcvtzs(toSize, fromSize, result, input);
                break;
            case I2F:
            case I2D:
            case L2F:
            case L2D:
                masm.scvtf(toSize, fromSize, result, input);
                break;
            case D2F:
            case F2D:
                masm.fcvt(fromSize, result, input);
                break;
            default:
                throw GraalError.shouldNotReachHere();
        }
    }

}
