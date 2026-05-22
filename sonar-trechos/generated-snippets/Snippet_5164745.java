public class Snippet__5164745 {

    @Override
        protected Query beforeRewrite(Query query) {
            query = query.clone();

            JdbcDataContext dataContext = getDataContext();
            if (dataContext != null) {
                String identifierQuoteString = dataContext.getIdentifierQuoteString();
                if (identifierQuoteString != null) {
                    List<SelectItem> selectItems = query.getSelectClause().getItems();
                    for (SelectItem item : selectItems) {
                        String alias = item.getAlias();
                        if (needsQuoting(alias, identifierQuoteString)) {
                            item.setAlias(identifierQuoteString + alias + identifierQuoteString);
                        }
                    }
                    List<FromItem> fromItems = query.getFromClause().getItems();
                    for (FromItem item : fromItems) {
                        String alias = item.getAlias();
                        if (needsQuoting(alias, identifierQuoteString)) {
                            item.setAlias(identifierQuoteString + alias + identifierQuoteString);
                        }
                    }
                }
            }
            return query;
        }

}
