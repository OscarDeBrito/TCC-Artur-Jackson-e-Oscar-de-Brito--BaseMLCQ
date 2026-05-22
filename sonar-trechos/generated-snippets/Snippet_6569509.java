public class Snippet__6569509 {

    public final void rule__XSynchronizedExpression__Group__1() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalEntities.g:13463:1: ( rule__XSynchronizedExpression__Group__1__Impl rule__XSynchronizedExpression__Group__2 )
                // InternalEntities.g:13464:2: rule__XSynchronizedExpression__Group__1__Impl rule__XSynchronizedExpression__Group__2
                {
                pushFollow(FOLLOW_66);
                rule__XSynchronizedExpression__Group__1__Impl();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FOLLOW_2);
                rule__XSynchronizedExpression__Group__2();

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
