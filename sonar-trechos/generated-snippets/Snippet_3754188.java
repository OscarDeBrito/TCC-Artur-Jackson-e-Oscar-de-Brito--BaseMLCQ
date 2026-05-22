public class SQLServerSelectParser extends SQLSelectParser {

    public SQLServerSelectParser(String sql){
        super(new SQLServerExprParser(sql));
    }

    public SQLServerSelectParser(SQLExprParser exprParser){
        super(exprParser);
    }

    public SQLServerSelectParser(SQLExprParser exprParser, SQLSelectListCache selectListCache){
        super(exprParser, selectListCache);
    }

    public SQLSelect select() {
        SQLSelect select = new SQLSelect();

        if (lexer.token() == Token.WITH) {
            SQLWithSubqueryClause with = this.parseWith();
            select.setWithSubQuery(with);
        }

        select.setQuery(query());
        select.setOrderBy(parseOrderBy());

        if (select.getOrderBy() == null) {
            select.setOrderBy(parseOrderBy());
        }

        if (lexer.token() == Token.FOR) {
            lexer.nextToken();

            if (lexer.identifierEquals("BROWSE")) {
                lexer.nextToken();
                select.setForBrowse(true);
            } else if (lexer.identifierEquals("XML")) {
                lexer.nextToken();

                for (;;) {
                    if (lexer.identifierEquals("AUTO") //
                        || lexer.identifierEquals("TYPE") //
                        || lexer.identifierEquals("XMLSCHEMA") //
                    ) {
                        select.getForXmlOptions().add(lexer.stringVal());
                        lexer.nextToken();
                    } else if (lexer.identifierEquals("ELEMENTS")) {
                        lexer.nextToken();
                        if (lexer.identifierEquals("XSINIL")) {
                            lexer.nextToken();
                            select.getForXmlOptions().add("ELEMENTS XSINIL");
                        } else {
                            select.getForXmlOptions().add("ELEMENTS");
                        }
                    } else if (lexer.identifierEquals("PATH")) {
                        SQLExpr xmlPath = this.exprParser.expr();
                        select.setXmlPath(xmlPath);
                    } else {
                        break;
                    }
                    
                    if (lexer.token() == Token.COMMA) {
                        lexer.nextToken();
                        continue;
                    } else {
                        break;
                    }
                }
            } else {
                throw new ParserException("syntax error, not support option : " + lexer.token() + ", " + lexer.info());
            }
        }
        
        if (lexer.identifierEquals("OFFSET")) {
            lexer.nextToken();
            SQLExpr offset = this.expr();
            
            acceptIdentifier("ROWS");
            select.setOffset(offset);
            
            if (lexer.token() == Token.FETCH) {
                lexer.nextToken();
                acceptIdentifier("NEXT");
                
                SQLExpr rowCount = expr();
                acceptIdentifier("ROWS");
                acceptIdentifier("ONLY");
                select.setRowCount(rowCount);
            }
        }

        return select;
    }

    public SQLSelectQuery query(SQLObject parent, boolean acceptUnion) {
        if (lexer.token() == Token.LPAREN) {
            lexer.nextToken();

            SQLSelectQuery select = query();
            accept(Token.RPAREN);

            return queryRest(select, acceptUnion);
        }

        SQLServerSelectQueryBlock queryBlock = new SQLServerSelectQueryBlock();

        if (lexer.token() == Token.SELECT) {
            lexer.nextToken();

            if (lexer.token() == Token.COMMENT) {
                lexer.nextToken();
            }

            if (lexer.token() == Token.DISTINCT) {
                queryBlock.setDistionOption(SQLSetQuantifier.DISTINCT);
                lexer.nextToken();
            } else if (lexer.token() == Token.ALL) {
                queryBlock.setDistionOption(SQLSetQuantifier.ALL);
                lexer.nextToken();
            }

            if (lexer.token() == Token.TOP) {
                SQLServerTop top = this.createExprParser().parseTop();
                queryBlock.setTop(top);
            }

            parseSelectList(queryBlock);
        }

        if (lexer.token() == Token.INTO) {
            lexer.nextToken();

            SQLTableSource into = this.parseTableSource();
            queryBlock.setInto((SQLExprTableSource) into);
        }

        parseFrom(queryBlock);

        parseWhere(queryBlock);

        parseGroupBy(queryBlock);

        queryBlock.setOrderBy(this.exprParser.parseOrderBy());

        parseFetchClause(queryBlock);

        return queryRest(queryBlock, acceptUnion);
    }

    protected SQLServerExprParser createExprParser() {
        return new SQLServerExprParser(lexer);
    }

    protected SQLTableSource parseTableSourceRest(SQLTableSource tableSource) {
        if (lexer.token() == Token.WITH) {
            lexer.nextToken();
            accept(Token.LPAREN);

            for (;;) {
                SQLExpr expr = this.expr();
                SQLExprHint hint = new SQLExprHint(expr);
                hint.setParent(tableSource);
                tableSource.getHints().add(hint);
                if (lexer.token() == Token.COMMA) {
                    lexer.nextToken();
                    continue;
                } else {
                    break;
                }
            }

            accept(Token.RPAREN);
        }

        return super.parseTableSourceRest(tableSource);
    }
}
