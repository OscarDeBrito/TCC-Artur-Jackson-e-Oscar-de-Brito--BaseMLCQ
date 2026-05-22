public class Snippet__8080847 {

    public final void rule__XShortClosure__Group_0__0__Impl() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                // InternalBug462047Lang.g:7756:1: ( ( ( rule__XShortClosure__Group_0_0__0 ) ) )
                // InternalBug462047Lang.g:7757:1: ( ( rule__XShortClosure__Group_0_0__0 ) )
                {
                // InternalBug462047Lang.g:7757:1: ( ( rule__XShortClosure__Group_0_0__0 ) )
                // InternalBug462047Lang.g:7758:2: ( rule__XShortClosure__Group_0_0__0 )
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getXShortClosureAccess().getGroup_0_0()); 
                }
                // InternalBug462047Lang.g:7759:2: ( rule__XShortClosure__Group_0_0__0 )
                // InternalBug462047Lang.g:7759:3: rule__XShortClosure__Group_0_0__0
                {
                pushFollow(FOLLOW_2);
                rule__XShortClosure__Group_0_0__0();

                state._fsp--;
                if (state.failed) return ;

                }

                if ( state.backtracking==0 ) {
                   after(grammarAccess.getXShortClosureAccess().getGroup_0_0()); 
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
