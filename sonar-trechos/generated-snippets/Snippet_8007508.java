public class Snippet__8007508 {

    public final EObject ruleScenario1() throws RecognitionException {
            EObject current = null;

            EObject this_Scenario1_0 = null;



            	enterRule();

            try {
                // InternalParametersTestLanguageEx.g:102:2: (this_Scenario1_0= superScenario1 )
                // InternalParametersTestLanguageEx.g:103:2: this_Scenario1_0= superScenario1
                {
                if ( state.backtracking==0 ) {

                  		newCompositeNode(grammarAccess.getScenario1Access().getScenario1ParserRuleCall());
              	
                }
                pushFollow(FollowSets000.FOLLOW_2);
                this_Scenario1_0=superScenario1();

                state._fsp--;
                if (state.failed) return current;
                if ( state.backtracking==0 ) {

                  		current = this_Scenario1_0;
                  		afterParserOrEnumRuleCall();
              	
                }

                }

                if ( state.backtracking==0 ) {

                  	leaveRule();

                }
            }

                catch (RecognitionException re) {
                    recover(input,re);
                    appendSkippedTokens();
                }
            finally {
            }
            return current;
        }

}
