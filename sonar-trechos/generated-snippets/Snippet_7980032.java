public class Snippet__7980032 {

    public final void rule__Object__Group__4() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalBug304681TestLanguage.g:876:1: ( rule__Object__Group__4__Impl rule__Object__Group__5 )
                // InternalBug304681TestLanguage.g:877:2: rule__Object__Group__4__Impl rule__Object__Group__5
                {
                pushFollow(FollowSets000.FOLLOW_9);
                rule__Object__Group__4__Impl();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FollowSets000.FOLLOW_2);
                rule__Object__Group__5();

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
