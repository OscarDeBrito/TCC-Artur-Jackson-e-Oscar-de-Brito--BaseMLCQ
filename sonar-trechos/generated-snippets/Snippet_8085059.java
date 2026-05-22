public class Snippet__8085059 {

    public final void rule__FeatureOfThis__Group__1__Impl() throws RecognitionException {
            int rule__FeatureOfThis__Group__1__Impl_StartIndex = input.index();

            		int stackSize = keepStackSize();
        	
            try {
                if ( state.backtracking>0 && alreadyParsedRule(input, 769) ) { return ; }
                // InternalSimpleBeeLangTestLanguage.g:10732:1: ( ( ( rule__FeatureOfThis__FeatureNameAssignment_1 ) ) )
                // InternalSimpleBeeLangTestLanguage.g:10733:1: ( ( rule__FeatureOfThis__FeatureNameAssignment_1 ) )
                {
                // InternalSimpleBeeLangTestLanguage.g:10733:1: ( ( rule__FeatureOfThis__FeatureNameAssignment_1 ) )
                // InternalSimpleBeeLangTestLanguage.g:10734:2: ( rule__FeatureOfThis__FeatureNameAssignment_1 )
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getFeatureOfThisAccess().getFeatureNameAssignment_1()); 
                }
                // InternalSimpleBeeLangTestLanguage.g:10735:2: ( rule__FeatureOfThis__FeatureNameAssignment_1 )
                // InternalSimpleBeeLangTestLanguage.g:10735:3: rule__FeatureOfThis__FeatureNameAssignment_1
                {
                pushFollow(FollowSets000.FOLLOW_2);
                rule__FeatureOfThis__FeatureNameAssignment_1();

                state._fsp--;
                if (state.failed) return ;

                }

                if ( state.backtracking==0 ) {
                   after(grammarAccess.getFeatureOfThisAccess().getFeatureNameAssignment_1()); 
                }

                }


                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {
                if ( state.backtracking>0 ) { memoize(input, 769, rule__FeatureOfThis__Group__1__Impl_StartIndex); }

                	restoreStackSize(stackSize);

            }
            return ;
        }

}
