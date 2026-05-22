public class Snippet__6234592 {

    public final void rule__MdCategory__Group_4_0__1__Impl() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalMetaData.g:6468:1: ( ( ( rule__MdCategory__LabelAssignment_4_0_1 ) ) )
                // InternalMetaData.g:6469:1: ( ( rule__MdCategory__LabelAssignment_4_0_1 ) )
                {
                // InternalMetaData.g:6469:1: ( ( rule__MdCategory__LabelAssignment_4_0_1 ) )
                // InternalMetaData.g:6470:2: ( rule__MdCategory__LabelAssignment_4_0_1 )
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getMdCategoryAccess().getLabelAssignment_4_0_1()); 
                }
                // InternalMetaData.g:6471:2: ( rule__MdCategory__LabelAssignment_4_0_1 )
                // InternalMetaData.g:6471:3: rule__MdCategory__LabelAssignment_4_0_1
                {
                pushFollow(FOLLOW_2);
                rule__MdCategory__LabelAssignment_4_0_1();

                state._fsp--;
                if (state.failed) return ;

                }

                if ( state.backtracking==0 ) {
                   after(grammarAccess.getMdCategoryAccess().getLabelAssignment_4_0_1()); 
                }

                }


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
