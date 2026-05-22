public class H2MergerTokenFactory extends DefaultMergerTokenFactory {

    @Override
    public MergerToken createSetColumnTypeToDb(final DbEntity entity, DbAttribute columnOriginal,
            final DbAttribute columnNew) {
        return new SetColumnTypeToDb(entity, columnOriginal, columnNew) {

            @Override
            protected void appendPrefix(StringBuffer sqlBuffer, QuotingStrategy context) {
                sqlBuffer.append("ALTER TABLE ");
                sqlBuffer.append(context.quotedFullyQualifiedName(entity));
                sqlBuffer.append(" ALTER ");
                sqlBuffer.append(context.quotedName(columnNew));
                sqlBuffer.append(" ");
            }
        };
    }

    @Override
    public MergerToken createSetAllowNullToDb(DbEntity entity, DbAttribute column) {
        return new SetAllowNullToDb(entity, column) {

            @Override
            public List<String> createSql(DbAdapter adapter) {
                return Collections.singletonList("ALTER TABLE " + getEntity().getFullyQualifiedName()
                        + " ALTER COLUMN " + getColumn().getName() + " SET NULL");
            }

        };
    }

    @Override
    public MergerToken createSetPrimaryKeyToDb(DbEntity entity, Collection<DbAttribute> primaryKeyOriginal,
            Collection<DbAttribute> primaryKeyNew, String detectedPrimaryKeyName) {
        return new SetPrimaryKeyToDb(entity, primaryKeyOriginal, primaryKeyNew, detectedPrimaryKeyName) {

            @Override
            protected void appendDropOriginalPrimaryKeySQL(DbAdapter adapter, List<String> sqls) {
                sqls.add("ALTER TABLE " + adapter.getQuotingStrategy().quotedFullyQualifiedName(getEntity())
                        + " DROP PRIMARY KEY");
            }

        };
    }

    @Override
    public MergerToken createSetGeneratedFlagToDb(DbEntity entity, DbAttribute column, boolean isGenerated) {
        return new SetGeneratedFlagToDb(entity, column, isGenerated) {
            protected void appendAlterColumnClause(DbAdapter adapter, StringBuffer builder) {
                builder.append(" ALTER COLUMN ");
            }

            @Override
            protected void appendAutoIncrement(DbAdapter adapter, StringBuffer builder) {
                adapter.createTableAppendColumn(builder, this.getColumn());
            }

            @Override
            protected void appendDropAutoIncrement(DbAdapter adapter, StringBuffer builder) {
                throw new UnsupportedOperationException("Can't automatically drop AUTO_INCREMENT in H2 database. You should do this manually.");
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
    }
}
