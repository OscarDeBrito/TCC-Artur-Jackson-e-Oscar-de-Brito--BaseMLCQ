public class Snippet__8144651 {

    public final void entryRuleCallExpression() throws RecognitionException {
            int entryRuleCallExpression_StartIndex = input.index();
            try {
                if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return ; }
                // InternalExBeeLangTestLanguageParser.g:1031:1: ( ruleCallExpression EOF )
                // InternalExBeeLangTestLanguageParser.g:1032:1: ruleCallExpression EOF
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getCallExpressionRule()); 
                }
                pushFollow(FollowSets000.FOLLOW_1);
                ruleCallExpression();

                state._fsp--;
                if (state.failed) return ;
                if ( state.backtracking==0 ) {
                   after(grammarAccess.getCallExpressionRule()); 
                }
                match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {
                if ( state.backtracking>0 ) { memoize(input, 73, entryRuleCallExpression_StartIndex); }
            }
            return ;
        }

}
