public interface SubjectVisitor<D> {
            D visit(ScalarValue scalarValue, byte operation, D previous);

            D visit(NATest naTest, byte operation, D previous);

            D visit(StringLength stringLength, byte operation, D previous);

            D visit(VectorSize vectorSize, byte operation, D previous);

            D visit(ElementAt elementAt, byte operation, D previous);

            D visit(Dim dim, byte operation, D previous);
        }
