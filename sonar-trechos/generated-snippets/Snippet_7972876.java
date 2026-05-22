public class Snippet__7972876 {

    public final void rule__Model__NameAssignment_1_9_1() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalBug309949TestLanguage.g:3272:1: ( ( RULE_ID ) )
                // InternalBug309949TestLanguage.g:3273:2: ( RULE_ID )
                {
                // InternalBug309949TestLanguage.g:3273:2: ( RULE_ID )
                // InternalBug309949TestLanguage.g:3274:3: RULE_ID
                {
                 before(grammarAccess.getModelAccess().getNameIDTerminalRuleCall_1_9_1_0()); 
                match(input,RULE_ID,FollowSets000.FOLLOW_2); 
                 after(grammarAccess.getModelAccess().getNameIDTerminalRuleCall_1_9_1_0()); 

                }


                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {

                	restoreStackSize(stackSize);

            }
            return ;
        }

}
