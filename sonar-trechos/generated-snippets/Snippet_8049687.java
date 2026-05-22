public class Snippet__8049687 {

    public final void rule__XCastedExpression__Group__1() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalXbaseWithAnnotations.g:6835:1: ( rule__XCastedExpression__Group__1__Impl )
                // InternalXbaseWithAnnotations.g:6836:2: rule__XCastedExpression__Group__1__Impl
                {
                pushFollow(FOLLOW_2);
                rule__XCastedExpression__Group__1__Impl();

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
