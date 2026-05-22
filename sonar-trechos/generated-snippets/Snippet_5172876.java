public class JdbcSource implements ISource {
    private static final Logger logger = LoggerFactory.getLogger(JdbcSource.class);

    public static final int SOURCE_ID = 16;

    private JdbcConnector dataSource;

    //used by reflection
    public JdbcSource(KylinConfig config) {
        try {
            dataSource = SourceConnectorFactory.getJdbcConnector(config);
        } catch (Throwable e) {
            logger.warn("DataSource cannot be connected. This may not be required in a MapReduce job.", e);
        }
    }

    @Override
    public ISourceMetadataExplorer getSourceMetadataExplorer() {
        return new JdbcExplorer(dataSource);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I> I adaptToBuildEngine(Class<I> engineInterface) {
        if (engineInterface == IMRInput.class) {
            return (I) new JdbcHiveMRInput(dataSource);
        } else if (engineInterface == ISparkInput.class) {
            return (I) new JdbcHiveSparkInput(dataSource);
        } else {
            throw new RuntimeException("Cannot adapt to " + engineInterface);
        }
    }

    @Override
    public IReadableTable createReadableTable(TableDesc tableDesc, String uuid) {
        return new JdbcTable(dataSource, tableDesc);
    }

    @Override
    public SourcePartition enrichSourcePartitionBeforeBuild(IBuildable buildable, SourcePartition srcPartition) {
        SourcePartition result = SourcePartition.getCopyOf(srcPartition);
        result.setSegRange(null);
        return result;
    }

    @Override
    public ISampleDataDeployer getSampleDataDeployer() {
        return new JdbcExplorer(dataSource);
    }

    @Override
    public void unloadTable(String tableName, String project) throws IOException{
    }

    @Override
    public void close() throws IOException {
        if (dataSource != null)
            dataSource.close();
    }
}
