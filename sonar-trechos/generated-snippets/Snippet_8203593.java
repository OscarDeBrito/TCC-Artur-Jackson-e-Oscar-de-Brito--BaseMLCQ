public class Snippet__8203593 {

    public final boolean synpred9_PsiInternalSimpleBeeLangTestLanguage() {
            state.backtracking++;
            int start = input.mark();
            try {
                synpred9_PsiInternalSimpleBeeLangTestLanguage_fragment(); // can never throw exception
            } catch (RecognitionException re) {
                System.err.println("impossible: "+re);
            }
            boolean success = !state.failed;
            input.rewind(start);
            state.backtracking--;
            state.failed=false;
            return success;
        }

}
