public class Snippet__8051978 {

    public final void rule__ParserRuleParameters__Group_1_2__1() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalTwoParametersTestLanguage.g:1073:1: ( rule__ParserRuleParameters__Group_1_2__1__Impl )
                // InternalTwoParametersTestLanguage.g:1074:2: rule__ParserRuleParameters__Group_1_2__1__Impl
                {
                pushFollow(FollowSets000.FOLLOW_2);
                rule__ParserRuleParameters__Group_1_2__1__Impl();

                state._fsp--;
                if (state.failed) return ;

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
