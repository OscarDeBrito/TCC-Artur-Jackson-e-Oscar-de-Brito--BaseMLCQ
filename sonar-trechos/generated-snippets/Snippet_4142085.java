public interface Context {
    @Nonnull DatabaseProduct databaseProduct();
    Context withDatabaseProduct(@Nonnull DatabaseProduct databaseProduct);
    String databaseProductName();
    Context withDatabaseProductName(String databaseProductName);
    String databaseVersion();
    Context withDatabaseVersion(String databaseVersion);
    int databaseMajorVersion();
    Context withDatabaseMajorVersion(int databaseMajorVersion);
    int databaseMinorVersion();
    Context withDatabaseMinorVersion(int databaseMinorVersion);
    String identifierQuoteString();
    Context withIdentifierQuoteString(String identifierQuoteString);
    @Nonnull NullCollation nullCollation();
    Context withNullCollation(@Nonnull NullCollation nullCollation);
    @Nonnull RelDataTypeSystem dataTypeSystem();
    Context withDataTypeSystem(@Nonnull RelDataTypeSystem dataTypeSystem);
    JethroDataSqlDialect.JethroInfo jethroInfo();
    Context withJethroInfo(JethroDataSqlDialect.JethroInfo jethroInfo);
  }
