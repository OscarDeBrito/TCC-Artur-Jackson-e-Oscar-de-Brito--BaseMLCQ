public final class SparqlFieldQueryFactory implements FieldQueryFactory {

    private static SparqlFieldQueryFactory instance;

    public static SparqlFieldQueryFactory getInstance() {
        if (instance == null) {
            instance = new SparqlFieldQueryFactory();
        }
        return instance;
    }

    private SparqlFieldQueryFactory() {
        super();
    }

    @Override
    public SparqlFieldQuery createFieldQuery() {
        return new SparqlFieldQuery();
    }

    /**
     * Utility Method to create an {@link SparqlFieldQuery} based on the parse {@link FieldQuery}
     * 
     * @param parsedQuery
     *            the parsed Query
     */
    public static SparqlFieldQuery getSparqlFieldQuery(FieldQuery parsedQuery) {
        Logger logger = LoggerFactory.getLogger(SparqlFieldQueryFactory.class);

        if (parsedQuery == null) {
            logger.trace("Parsed query is null.");
            return null;
        } else if (parsedQuery instanceof SparqlFieldQuery) {
            logger.trace("Parsed query is a [SparqlFieldQuery].");
            return (SparqlFieldQuery) parsedQuery;
        } else {
            logger.trace("Parsed query is a [{}].", parsedQuery.getClass().toString());
            return parsedQuery.copyTo(new SparqlFieldQuery());
        }
    }

}
