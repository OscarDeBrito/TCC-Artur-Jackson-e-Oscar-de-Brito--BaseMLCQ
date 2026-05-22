@Deprecated
public class PigStorageSchema extends PigStorage implements LoadMetadata, StoreMetadata {
    public PigStorageSchema() {
        this("\t");
    }

    public PigStorageSchema(String delim) {
        super(delim, "-schema");
    }
}
