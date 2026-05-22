public class Snippet__6824306 {

    public final void norm1_LogicalORExpression__Group_1_0_0__1() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                {
                pushFollow(FOLLOW_2);
                norm1_LogicalORExpression__Group_1_0_0__1__Impl();

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
