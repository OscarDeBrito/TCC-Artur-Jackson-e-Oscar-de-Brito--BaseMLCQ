public class Snippet__7462222 {

    public static final class StoreConstantOp extends MemOp implements SPARCTailDelayedLIRInstruction {
            public static final LIRInstructionClass<StoreConstantOp> TYPE = LIRInstructionClass.create(StoreConstantOp.class);
            public static final SizeEstimate SIZE = SizeEstimate.create(2);

            protected final JavaConstant input;

            public StoreConstantOp(PlatformKind kind, SPARCAddressValue address, JavaConstant input, LIRFrameState state) {
                super(TYPE, SIZE, kind, address, state);
                this.input = input;
                if (!input.isDefaultForKind()) {
                    throw GraalError.shouldNotReachHere("Can only store null constants to memory");
                }
            }

            @Override
            public void emitMemAccess(CompilationResultBuilder crb, SPARCMacroAssembler masm) {
                try (ScratchRegister sc = masm.getScratchRegister()) {
                    Register scratch = sc.getRegister();
                    SPARCAddress addr = generateSimm13OffsetLoad(address.toAddress(), masm, scratch);
                    getDelayedControlTransfer().emitControlTransfer(crb, masm);
                    if (state != null) {
                        crb.recordImplicitException(masm.position(), state);
                    }
                    int byteCount = kind.getSizeInBytes();
                    masm.st(g0, addr, byteCount);
                }
            }
        }

}
