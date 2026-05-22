public class Snippet__6829907 {

    public final void rule__FunctionTypeExpressionOLD__Group__9() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                {
                pushFollow(FOLLOW_2);
                rule__FunctionTypeExpressionOLD__Group__9__Impl();

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
