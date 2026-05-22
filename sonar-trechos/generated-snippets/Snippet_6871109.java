public class Snippet__6871109 {

    public final void rule__DefaultFormalParameter__HasInitializerAssignmentAssignment_0() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalTypesParser.g:15171:1: ( ( ( EqualsSign ) ) )
                // InternalTypesParser.g:15172:2: ( ( EqualsSign ) )
                {
                // InternalTypesParser.g:15172:2: ( ( EqualsSign ) )
                // InternalTypesParser.g:15173:3: ( EqualsSign )
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getDefaultFormalParameterAccess().getHasInitializerAssignmentEqualsSignKeyword_0_0()); 
                }
                // InternalTypesParser.g:15174:3: ( EqualsSign )
                // InternalTypesParser.g:15175:4: EqualsSign
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getDefaultFormalParameterAccess().getHasInitializerAssignmentEqualsSignKeyword_0_0()); 
                }
                match(input,EqualsSign,FOLLOW_2); if (state.failed) return ;
                if ( state.backtracking==0 ) {
                   after(grammarAccess.getDefaultFormalParameterAccess().getHasInitializerAssignmentEqualsSignKeyword_0_0()); 
                }

                }

                if ( state.backtracking==0 ) {
                   after(grammarAccess.getDefaultFormalParameterAccess().getHasInitializerAssignmentEqualsSignKeyword_0_0()); 
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
