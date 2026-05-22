public static class Query extends AbstractLogEntry
    {
        private final String query;

        public Query(String query, QueryOptions queryOptions, QueryState queryState, long queryStartTime)
        {
            super(queryOptions, queryState, queryStartTime);
            this.query = query;
        }

        @Override
        protected String type()
        {
            return SINGLE_QUERY;
        }

        @Override
        public void writeMarshallable(WireOut wire)
        {
            super.writeMarshallable(wire);
            wire.write(QUERY).text(query);
        }

        @Override
        public int weight()
        {
            return Ints.checkedCast(ObjectSizes.sizeOf(query)) + super.weight();
        }
    }
