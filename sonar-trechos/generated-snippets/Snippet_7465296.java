public static class MembarOp extends AArch64LIRInstruction {
        public static final LIRInstructionClass<MembarOp> TYPE = LIRInstructionClass.create(MembarOp.class);

        // For future use.
        @SuppressWarnings("unused") private final int barriers;

        public MembarOp(int barriers) {
            super(TYPE);
            this.barriers = barriers;
        }

        @Override
        // The odd-looking @SuppressWarnings("all") is here because of
        // a compiler bug which warns that crb is unused, and also
        // warns that @SuppressWarnings("unused") is unnecessary.
        public void emitCode(@SuppressWarnings("all") CompilationResultBuilder crb, AArch64MacroAssembler masm) {
            assert barriers >= MemoryBarriers.LOAD_LOAD && barriers <= (MemoryBarriers.STORE_STORE | MemoryBarriers.STORE_LOAD | MemoryBarriers.LOAD_STORE | MemoryBarriers.LOAD_LOAD);
            switch (barriers) {
                case MemoryBarriers.STORE_STORE:
                    masm.dmb(AArch64MacroAssembler.BarrierKind.STORE_STORE);
                    break;
                case MemoryBarriers.LOAD_LOAD:
                case MemoryBarriers.LOAD_STORE:
                case MemoryBarriers.LOAD_LOAD | MemoryBarriers.LOAD_STORE:
                    masm.dmb(AArch64MacroAssembler.BarrierKind.LOAD_LOAD);
                    break;
                default:
                    masm.dmb(AArch64MacroAssembler.BarrierKind.ANY_ANY);
                    break;
            }
        }
    }
