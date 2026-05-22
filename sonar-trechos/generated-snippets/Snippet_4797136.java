@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExpressionParserFactory {
    
    /**
     * Create alias parser instance.
     * 
     * @param lexerEngine lexical analysis engine.
     * @return alias parser instance
     */
    public static AliasExpressionParser createAliasExpressionParser(final LexerEngine lexerEngine) {
        switch (lexerEngine.getDatabaseType()) {
            case H2:
                return new MySQLAliasExpressionParser(lexerEngine);
            case MySQL:
                return new MySQLAliasExpressionParser(lexerEngine);
            case Oracle:
                return new OracleAliasExpressionParser(lexerEngine);
            case SQLServer:
                return new SQLServerAliasExpressionParser(lexerEngine);
            case PostgreSQL:
                return new PostgreSQLAliasExpressionParser(lexerEngine);
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database type: %s", lexerEngine.getDatabaseType()));
        }
    }
    
    /**
     * Create expression parser instance.
     *
     * @param lexerEngine lexical analysis engine.
     * @return expression parser instance
     */
    public static BasicExpressionParser createBasicExpressionParser(final LexerEngine lexerEngine) {
        return new BasicExpressionParser(lexerEngine);
    }
}
