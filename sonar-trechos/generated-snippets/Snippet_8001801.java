public class Snippet__8001801 {

    public final boolean synpred87_InternalBacktrackingContentAssistTestLanguage() {
            state.backtracking++;
            int start = input.mark();
            try {
                synpred87_InternalBacktrackingContentAssistTestLanguage_fragment(); // can never throw exception
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
