public class Snippet__8087321 {

    public final EObject ruleModel() throws RecognitionException {
            EObject current = null;

            Token lv_name_0_0=null;


            	enterRule();

            try {
                // InternalLiveContainerBuilderIntegerationTestLanguage.g:77:2: ( ( (lv_name_0_0= RULE_ID ) ) )
                // InternalLiveContainerBuilderIntegerationTestLanguage.g:78:2: ( (lv_name_0_0= RULE_ID ) )
                {
                // InternalLiveContainerBuilderIntegerationTestLanguage.g:78:2: ( (lv_name_0_0= RULE_ID ) )
                // InternalLiveContainerBuilderIntegerationTestLanguage.g:79:3: (lv_name_0_0= RULE_ID )
                {
                // InternalLiveContainerBuilderIntegerationTestLanguage.g:79:3: (lv_name_0_0= RULE_ID )
                // InternalLiveContainerBuilderIntegerationTestLanguage.g:80:4: lv_name_0_0= RULE_ID
                {
                lv_name_0_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); 

                				newLeafNode(lv_name_0_0, grammarAccess.getModelAccess().getNameIDTerminalRuleCall_0());
            			

                				if (current==null) {
                					current = createModelElement(grammarAccess.getModelRule());
                				}
                				setWithLastConsumed(
                					current,
                					"name",
                					lv_name_0_0,
                					"org.eclipse.xtext.common.Terminals.ID");
            			

                }


                }


                }


                	leaveRule();

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
