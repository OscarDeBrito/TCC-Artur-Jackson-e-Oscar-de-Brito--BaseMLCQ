public class Snippet__8030414 {

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
                TokenStream input = (TokenStream)_input;
            	int _s = s;
                switch ( s ) {
                        case 0 : 
                            int LA102_1 = input.LA(1);

                         
                            int index102_1 = input.index();
                            input.rewind();
                            s = -1;
                            if ( (synpred145_InternalDomainmodel()) ) {s = 77;}

                            else if ( (true) ) {s = 2;}

                         
                            input.seek(index102_1);
                            if ( s>=0 ) return s;
                            break;
                }
                if (state.backtracking>0) {state.failed=true; return -1;}
                NoViableAltException nvae =
                    new NoViableAltException(getDescription(), 102, _s, input);
                error(nvae);
                throw nvae;
            }

}
