public class Snippet__6579602 {

    public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
                TokenStream input = (TokenStream)_input;
            	int _s = s;
                switch ( s ) {
                        case 0 : 
                            int LA21_1 = input.LA(1);

                         
                            int index21_1 = input.index();
                            input.rewind();
                            s = -1;
                            if ( (synpred42_InternalEntities()) ) {s = 3;}

                            else if ( (true) ) {s = 5;}

                         
                            input.seek(index21_1);
                            if ( s>=0 ) return s;
                            break;
                        case 1 : 
                            int LA21_2 = input.LA(1);

                         
                            int index21_2 = input.index();
                            input.rewind();
                            s = -1;
                            if ( (synpred42_InternalEntities()) ) {s = 3;}

                            else if ( (true) ) {s = 5;}

                         
                            input.seek(index21_2);
                            if ( s>=0 ) return s;
                            break;
                }
                if (state.backtracking>0) {state.failed=true; return -1;}
                NoViableAltException nvae =
                    new NoViableAltException(getDescription(), 21, _s, input);
                error(nvae);
                throw nvae;
            }

}
