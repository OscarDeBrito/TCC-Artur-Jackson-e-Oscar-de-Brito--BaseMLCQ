public static class Operator extends JexlException {
        /**
         * Creates a new Operator exception instance.
         *
         * @param node  the location information
         * @param symbol  the operator name
         * @param cause the exception causing the error
         */
        public Operator(JexlNode node, String symbol, Throwable cause) {
            super(node, symbol, cause);
        }

        /**
         * @return the method name
         */
        public String getSymbol() {
            return super.detailedMessage();
        }

        @Override
        protected String detailedMessage() {
            return "error calling operator '" + getSymbol() + "'";
        }
    }
