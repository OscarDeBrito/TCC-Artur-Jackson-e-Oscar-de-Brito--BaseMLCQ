public class Snippet__8119264 {

    public final void rule__WithContextExpression__Group_3__0() throws RecognitionException {
            int rule__WithContextExpression__Group_3__0_StartIndex = input.index();

            		int stackSize = keepStackSize();
        	
            try {
                if ( state.backtracking>0 && alreadyParsedRule(input, 692) ) { return ; }
                // InternalSimpleBeeLangTestLanguage.g:9695:1: ( rule__WithContextExpression__Group_3__0__Impl rule__WithContextExpression__Group_3__1 )
                // InternalSimpleBeeLangTestLanguage.g:9696:2: rule__WithContextExpression__Group_3__0__Impl rule__WithContextExpression__Group_3__1
                {
                pushFollow(FollowSets000.FOLLOW_9);
                rule__WithContextExpression__Group_3__0__Impl();

                state._fsp--;
                if (state.failed) return ;
                pushFollow(FollowSets000.FOLLOW_2);
                rule__WithContextExpression__Group_3__1();

                state._fsp--;
                if (state.failed) return ;

                }

            }
            catch (RecognitionException re) {
                reportError(re);
                recover(input,re);
            }
            finally {
                if ( state.backtracking>0 ) { memoize(input, 692, rule__WithContextExpression__Group_3__0_StartIndex); }

                	restoreStackSize(stackSize);

            }
            return ;
        }

}
