public class Snippet__8271243 {

    public final Boolean entryRuleType() throws RecognitionException {
            Boolean current = false;

            Boolean iv_ruleType = null;


            try {
                // PsiInternalSDomain.g:284:46: (iv_ruleType= ruleType EOF )
                // PsiInternalSDomain.g:285:2: iv_ruleType= ruleType EOF
                {
                 markComposite(elementTypeProvider.getTypeElementType()); 
                pushFollow(FOLLOW_1);
                iv_ruleType=ruleType();

                state._fsp--;

                 current =iv_ruleType; 
                match(input,EOF,FOLLOW_2); 

                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {
            }
            return current;
        }

}
