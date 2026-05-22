public class Snippet__8102140 {

    public final void rule__Disjunction__Group_1__0() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalXtext.g:5342:1: ( rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1 )
                // InternalXtext.g:5343:2: rule__Disjunction__Group_1__0__Impl rule__Disjunction__Group_1__1
                {
                pushFollow(FollowSets000.FOLLOW_26);
                rule__Disjunction__Group_1__0__Impl();

                state._fsp--;

                pushFollow(FollowSets000.FOLLOW_2);
                rule__Disjunction__Group_1__1();

                state._fsp--;


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
