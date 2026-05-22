public class Snippet__8255035 {

    public final void rule__XIfExpression__Group__6() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalEntities.g:9109:1: ( rule__XIfExpression__Group__6__Impl )
                // InternalEntities.g:9110:2: rule__XIfExpression__Group__6__Impl
                {
                pushFollow(FOLLOW_2);
                rule__XIfExpression__Group__6__Impl();

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
