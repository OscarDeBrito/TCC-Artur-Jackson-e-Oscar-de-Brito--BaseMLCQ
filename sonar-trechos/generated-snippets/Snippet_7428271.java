public class Snippet__7428271 {

    @Opcode("SETcc")
        public static final class CondSetOp extends AMD64LIRInstruction {
            public static final LIRInstructionClass<CondSetOp> TYPE = LIRInstructionClass.create(CondSetOp.class);
            @Def({REG, HINT}) protected Value result;
            private final ConditionFlag condition;

            public CondSetOp(Variable result, Condition condition) {
                super(TYPE);
                this.result = result;
                this.condition = intCond(condition);
            }

            @Override
            public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
                setcc(masm, result, condition);
            }
        }

}
