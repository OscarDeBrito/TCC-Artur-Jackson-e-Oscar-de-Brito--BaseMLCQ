abstract class BinaryOperatorHelper extends OperatorHelper implements BiPredicate<Type, Type> {

        BinaryOperatorHelper(Tag tag) {
            super(tag);
        }

        /**
         * This routine implements the binary operator lookup process. It customizes the behavior
         * of the shared lookup routine in {@link OperatorHelper}, by using an unary applicability test
         * (see {@link BinaryOperatorHelper#isBinaryOperatorApplicable(OperatorSymbol, Type, Type)}
         */
        final OperatorSymbol doLookup(Type t1, Type t2) {
            return doLookup(op -> isBinaryOperatorApplicable(op, t1, t2));
        }

        /**
         * Binary operator applicability test - are the input types the same as the expected operand types?
         */
        boolean isBinaryOperatorApplicable(OperatorSymbol op, Type t1, Type t2) {
            List<Type> formals = op.type.getParameterTypes();
            return types.isSameType(formals.head, t1) &&
                    types.isSameType(formals.tail.head, t2);
        }

        /**
         * Adds a binary operator symbol.
         */
        final BinaryOperatorHelper addBinaryOperator(OperatorType arg1, OperatorType arg2, OperatorType res, int... opcode) {
            operatorSuppliers = operatorSuppliers.prepend(() -> makeOperator(name, List.of(arg1, arg2), res, opcode));
            return this;
        }

        /**
         * This method will be overridden by binary operator helpers to provide custom resolution
         * logic.
         */
        abstract OperatorSymbol resolve(Type t1, Type t2);
    }
