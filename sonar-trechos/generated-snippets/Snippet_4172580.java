public class DbSyncModule implements Module {

    /**
     * A DI container key for the Map&lt;String, String&gt; storing properties
     * used by built-in Cayenne service.
     */
    public static final String MERGER_FACTORIES_MAP = "cayenne.dbsync.mergerfactories";

    public static MapBuilder<MergerTokenFactory> contributeMergerTokenFactories(Binder binder) {
        return binder.bindMap(MergerTokenFactory.class, MERGER_FACTORIES_MAP);
    }

    @Override
    public void configure(Binder binder) {

        // default and per adapter merger factories...
        binder.bind(MergerTokenFactory.class).to(DefaultMergerTokenFactory.class);
        contributeMergerTokenFactories(binder)
                .put(DB2Adapter.class.getName(), DB2MergerTokenFactory.class)
                .put(DerbyAdapter.class.getName(), DerbyMergerTokenFactory.class)
                .put(FirebirdAdapter.class.getName(), FirebirdMergerTokenFactory.class)
                .put(H2Adapter.class.getName(), H2MergerTokenFactory.class)
                .put(HSQLDBAdapter.class.getName(), HSQLMergerTokenFactory.class)
                .put(IngresAdapter.class.getName(), IngresMergerTokenFactory.class)
                .put(MySQLAdapter.class.getName(), MySQLMergerTokenFactory.class)
                .put(OpenBaseAdapter.class.getName(), OpenBaseMergerTokenFactory.class)
                .put(OracleAdapter.class.getName(), OracleMergerTokenFactory.class)
                .put(Oracle8Adapter.class.getName(), OracleMergerTokenFactory.class)
                .put(PostgresAdapter.class.getName(), PostgresMergerTokenFactory.class)
                .put(SQLServerAdapter.class.getName(), SQLServerMergerTokenFactory.class)
                .put(SybaseAdapter.class.getName(), SybaseMergerTokenFactory.class);

        binder.bind(MergerTokenFactoryProvider.class).to(MergerTokenFactoryProvider.class);

    }
}
