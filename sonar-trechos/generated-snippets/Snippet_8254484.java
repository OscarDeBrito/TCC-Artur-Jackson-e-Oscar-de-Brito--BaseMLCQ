public class Snippet__8254484 {

    public final void entryRuleXBasicForLoopExpression() throws RecognitionException {
            try {
                // InternalEntities.g:1159:1: ( ruleXBasicForLoopExpression EOF )
                // InternalEntities.g:1160:1: ruleXBasicForLoopExpression EOF
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getXBasicForLoopExpressionRule()); 
                }
                pushFollow(FOLLOW_1);
                ruleXBasicForLoopExpression();

                state._fsp--;
                if (state.failed) return ;
                if ( state.backtracking==0 ) {
                   after(grammarAccess.getXBasicForLoopExpressionRule()); 
                }
                match(input,EOF,FOLLOW_2); if (state.failed) return ;

                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {
            }
            return ;
        }

}
