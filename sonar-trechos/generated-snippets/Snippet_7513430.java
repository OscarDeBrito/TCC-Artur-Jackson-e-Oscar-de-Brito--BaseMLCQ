public class Snippet__7513430 {

    public static final class ConstShuffleBytesOp extends AMD64LIRInstruction {
            public static final LIRInstructionClass<ConstShuffleBytesOp> TYPE = LIRInstructionClass.create(ConstShuffleBytesOp.class);
            @Def({REG}) protected AllocatableValue result;
            @Use({REG}) protected AllocatableValue source;
            private final byte[] selector;

            public ConstShuffleBytesOp(AllocatableValue result, AllocatableValue source, byte... selector) {
                super(TYPE);
                assert AVXKind.getRegisterSize(((AMD64Kind) result.getPlatformKind())).getBytes() == selector.length;
                this.result = result;
                this.source = source;
                this.selector = selector;
            }

            @Override
            public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
                AMD64Kind kind = (AMD64Kind) result.getPlatformKind();
                AMD64Address address = (AMD64Address) crb.recordDataReferenceInCode(selector, selector.length);
                VPSHUFB.emit(masm, AVXKind.getRegisterSize(kind), asRegister(result), asRegister(source), address);
            }
        }

}
