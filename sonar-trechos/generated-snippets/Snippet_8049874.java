public class Snippet__8049874 {

    public final void rule__MandatoryModel__Group__2() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalUnorderedGroupsTestLanguage.g:1164:1: ( rule__MandatoryModel__Group__2__Impl rule__MandatoryModel__Group__3 )
                // InternalUnorderedGroupsTestLanguage.g:1165:2: rule__MandatoryModel__Group__2__Impl rule__MandatoryModel__Group__3
                {
                pushFollow(FollowSets000.FOLLOW_6);
                rule__MandatoryModel__Group__2__Impl();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FollowSets000.FOLLOW_2);
                rule__MandatoryModel__Group__3();

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
