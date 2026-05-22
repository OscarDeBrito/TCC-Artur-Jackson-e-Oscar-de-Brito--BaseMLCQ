@Opcode
public class AMD64DecrementingSafepointCheckOp extends AMD64LIRInstruction {

    public static final LIRInstructionClass<AMD64DecrementingSafepointCheckOp> TYPE = LIRInstructionClass.create(AMD64DecrementingSafepointCheckOp.class);

    protected AMD64DecrementingSafepointCheckOp() {
        super(TYPE);
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        assert SubstrateOptions.MultiThreaded.getValue();
        SubstrateRegisterConfig threadRegister = (SubstrateRegisterConfig) crb.codeCache.getRegisterConfig();
        masm.decrementl(new AMD64Address(threadRegister.getThreadRegister(), Math.toIntExact(Safepoint.getThreadLocalSafepointRequestedOffset())), 1);
    }
}
