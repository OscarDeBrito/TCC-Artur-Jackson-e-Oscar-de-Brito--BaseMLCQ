public class Snippet__7962366 {

    public final void ruleXDoWhileExpression() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalXImportSectionTestLang.g:1067:2: ( ( ( rule__XDoWhileExpression__Group__0 ) ) )
                // InternalXImportSectionTestLang.g:1068:2: ( ( rule__XDoWhileExpression__Group__0 ) )
                {
                // InternalXImportSectionTestLang.g:1068:2: ( ( rule__XDoWhileExpression__Group__0 ) )
                // InternalXImportSectionTestLang.g:1069:3: ( rule__XDoWhileExpression__Group__0 )
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getXDoWhileExpressionAccess().getGroup()); 
                }
                // InternalXImportSectionTestLang.g:1070:3: ( rule__XDoWhileExpression__Group__0 )
                // InternalXImportSectionTestLang.g:1070:4: rule__XDoWhileExpression__Group__0
                {
                pushFollow(FOLLOW_2);
                rule__XDoWhileExpression__Group__0();

                state._fsp--;
                if (state.failed) return ;

                }

                if ( state.backtracking==0 ) {
                   after(grammarAccess.getXDoWhileExpressionAccess().getGroup()); 
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
