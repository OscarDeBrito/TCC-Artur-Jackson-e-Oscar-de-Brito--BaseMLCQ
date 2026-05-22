public class Snippet__6823557 {

    public final void norm3_RelationalExpression__Group__1__Impl() throws RecognitionException {

            		int stackSize = keepStackSize();
        	
            try {
                {
                {
                if ( state.backtracking==0 ) {
                   before(grammarAccess.getRelationalExpressionAccess().getGroup_1()); 
                }
                loop421:
                do {
                    int alt421=2;
                    int LA421_0 = input.LA(1);

                    if ( (LA421_0==GreaterThanSign) ) {
                        int LA421_2 = input.LA(2);

                        if ( ((LA421_2>=Intersection && LA421_2<=Implements)||(LA421_2>=Promisify && LA421_2<=Abstract)||(LA421_2>=External && LA421_2<=Function)||(LA421_2>=Private && LA421_2<=Delete)||LA421_2==Public||LA421_2==Static||(LA421_2>=Target && LA421_2<=Await)||LA421_2==Class||(LA421_2>=False && LA421_2<=Super)||LA421_2==Union||LA421_2==This||(LA421_2>=From && LA421_2<=Void)||(LA421_2>=Get && LA421_2<=Set)||LA421_2==PlusSignPlusSign||LA421_2==HyphenMinusHyphenMinus||LA421_2==SolidusEqualsSign||LA421_2==As||LA421_2==Of||LA421_2==ExclamationMark||LA421_2==LeftParenthesis||LA421_2==PlusSign||LA421_2==HyphenMinus||LA421_2==Solidus||(LA421_2>=LessThanSign && LA421_2<=EqualsSign)||(LA421_2>=CommercialAt && LA421_2<=LeftSquareBracket)||LA421_2==LeftCurlyBracket||LA421_2==Tilde||LA421_2==RULE_DOUBLE||(LA421_2>=RULE_HEX_INT && LA421_2<=RULE_LEGACY_OCTAL_INT)||LA421_2==RULE_SCIENTIFIC_INT||LA421_2==RULE_IDENTIFIER||LA421_2==RULE_STRING||(LA421_2>=RULE_TEMPLATE_HEAD && LA421_2<=RULE_NO_SUBSTITUTION_TEMPLATE_LITERAL)||LA421_2==RULE_INT) ) {
                            alt421=1;
                        }


                    }
                    else if ( (LA421_0==Instanceof||LA421_0==LessThanSignEqualsSign||LA421_0==In||LA421_0==LessThanSign) ) {
                        alt421=1;
                    }


                    switch (alt421) {
                	case 1 :
                	    {
                	    pushFollow(FOLLOW_126);
                	    norm3_RelationalExpression__Group_1__0();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop421;
                    }
                } while (true);

                if ( state.backtracking==0 ) {
                   after(grammarAccess.getRelationalExpressionAccess().getGroup_1()); 
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
