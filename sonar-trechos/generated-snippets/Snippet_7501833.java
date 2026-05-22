public class Snippet__7501833 {

    public abstract static class AbstractDSLExpressionReducer implements DSLExpressionReducer {

            @Override
            public DSLExpression visitBinary(Binary binary) {
                return binary;
            }

            @Override
            public DSLExpression visitCall(Call binary) {
                return binary;
            }

            @Override
            public DSLExpression visitNegate(Negate negate) {
                return negate;
            }

            @Override
            public DSLExpression visitVariable(Variable binary) {
                return binary;
            }

        }

}
