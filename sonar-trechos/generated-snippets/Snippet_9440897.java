public class Snippet__9440897 {

    @Override
        public void accept( final Visitor v ) {
            v.visitStackConsumer(this);
            v.visitExceptionThrower(this);
            v.visitTypedInstruction(this);
            v.visitArrayInstruction(this);
            v.visitIASTORE(this);
        }

}
