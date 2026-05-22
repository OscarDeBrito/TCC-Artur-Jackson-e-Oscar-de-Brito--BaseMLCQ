public class Snippet__8224570 {

    public final Boolean ruleValueLiteral() throws RecognitionException {
            Boolean current = false;
            int ruleValueLiteral_StartIndex = input.index();
            Token lv_value_0_0=null;

            try {
                if ( state.backtracking>0 && alreadyParsedRule(input, 108) ) { return current; }
                // PsiInternalBeeLangTestLanguage.g:5259:1: ( ( (lv_value_0_0= RULE_STRING ) ) )
                // PsiInternalBeeLangTestLanguage.g:5260:2: ( (lv_value_0_0= RULE_STRING ) )
                {
                // PsiInternalBeeLangTestLanguage.g:5260:2: ( (lv_value_0_0= RULE_STRING ) )
                // PsiInternalBeeLangTestLanguage.g:5261:3: (lv_value_0_0= RULE_STRING )
                {
                // PsiInternalBeeLangTestLanguage.g:5261:3: (lv_value_0_0= RULE_STRING )
                // PsiInternalBeeLangTestLanguage.g:5262:4: lv_value_0_0= RULE_STRING
                {
                if ( state.backtracking==0 ) {

                  				markLeaf(elementTypeProvider.getValueLiteral_ValueSTRINGTerminalRuleCall_0ElementType());
              			
                }
                lv_value_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
                if ( state.backtracking==0 ) {

                  				if(!current) {
                  					associateWithSemanticElement();
                  					current = true;
                  				}
              			
                }
                if ( state.backtracking==0 ) {

                  				doneLeaf(lv_value_0_0);
              			
                }

                }


                }


                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {
                if ( state.backtracking>0 ) { memoize(input, 108, ruleValueLiteral_StartIndex); }
            }
            return current;
        }

}
