private class LLVMNullerReadVisitor extends LLVMLocalReadVisitor {
        private final int[] lastInstructionIndexTouchingLocal;
        private int instructionIndex;

        LLVMNullerReadVisitor(int[] lastInstructionIndexTouchingLocal) {
            this.lastInstructionIndexTouchingLocal = lastInstructionIndexTouchingLocal;
        }

        public void setInstructionIndex(int instructionIndex) {
            this.instructionIndex = instructionIndex;
        }

        @Override
        public void visitLocalRead(SymbolImpl symbol) {
            int frameSlotIndex = resolve(symbol);
            if (frameSlotIndex >= 0) {
                lastInstructionIndexTouchingLocal[frameSlotIndex] = instructionIndex;
            }
        }
    }
