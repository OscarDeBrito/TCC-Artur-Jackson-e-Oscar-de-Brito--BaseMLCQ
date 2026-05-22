private static class FalseEquals implements Predicate<Boolean>
    {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean accept(Boolean anObject)
        {
            return Boolean.FALSE.equals(anObject);
        }
    }
