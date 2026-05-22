public class DataSourceIndex implements IDataSourceIndex<String, DataSourceId> {

    private final Index index;
    private final String dataset;
    private final String dataverse;
    private final MetadataProvider metadataProvider;

    // Every transactions needs to work with its own instance of an
    // MetadataProvider.
    public DataSourceIndex(Index index, String dataverse, String dataset, MetadataProvider metadataProvider) {
        this.index = index;
        this.dataset = dataset;
        this.dataverse = dataverse;
        this.metadataProvider = metadataProvider;
    }

    // TODO: Maybe Index can directly implement IDataSourceIndex<String, DataSourceId>
    @Override
    public IDataSource<DataSourceId> getDataSource() {
        try {
            DataSourceId sourceId = new DataSourceId(dataverse, dataset);
            return metadataProvider.lookupSourceInMetadata(sourceId);
        } catch (Exception me) {
            return null;
        }
    }

    @Override
    public String getId() {
        return index.getIndexName();
    }

    public Index getIndex() {
        return index;
    }
}
