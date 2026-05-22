public class Snippet__9393147 {

    @Override
        public void visitTryCatchBlock(Label start, Label end, Label handler,
                String type) {
            if (!inlining) {
                // try-catch blocks are saved here and replayed at the end
                // of the method (in visitMaxs)
                blocks.add(new CatchBlock(start, end, handler, type));
            } else {
                super.visitTryCatchBlock(start, end, handler, type);
            }
        }

}
