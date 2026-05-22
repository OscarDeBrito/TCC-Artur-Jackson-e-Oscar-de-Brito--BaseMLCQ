private static class Empty extends Predicates<String>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(String anObject)
        {
            return anObject != null && anObject.length() == 0;
        }

        @Override
        public String toString()
        {
            return "StringPredicates.empty()";
        }
    }
