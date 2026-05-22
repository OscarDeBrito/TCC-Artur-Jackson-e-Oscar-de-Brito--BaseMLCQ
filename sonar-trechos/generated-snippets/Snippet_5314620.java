public class Snippet__5314620 {

    @Override
        public void process(Operator operator, List<COSBase> arguments) throws MissingOperandException
        {
            if (arguments.size() < 6)
            {
                throw new MissingOperandException(operator, arguments);
            }
            if (!checkArrayTypesClass(arguments, COSNumber.class))
            {
                return;
            }

            COSNumber a = (COSNumber)arguments.get( 0 );
            COSNumber b = (COSNumber)arguments.get( 1 );
            COSNumber c = (COSNumber)arguments.get( 2 );
            COSNumber d = (COSNumber)arguments.get( 3 );
            COSNumber e = (COSNumber)arguments.get( 4 );
            COSNumber f = (COSNumber)arguments.get( 5 );

            Matrix matrix = new Matrix(a.floatValue(), b.floatValue(), c.floatValue(),
                                       d.floatValue(), e.floatValue(), f.floatValue());

            context.setTextMatrix(matrix);
            context.setTextLineMatrix(matrix.clone());
        }

}
