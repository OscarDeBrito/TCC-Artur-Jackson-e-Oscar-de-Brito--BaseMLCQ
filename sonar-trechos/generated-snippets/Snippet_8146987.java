public class Tan extends FunctionExpression {
    public Tan(List<Expression> arguments) {
        super("TAN", arguments);
    }

    @Override
    public FunctionExpression constructFunction(List<Expression> arguments) {
        return new Tan(arguments);
    }

}
