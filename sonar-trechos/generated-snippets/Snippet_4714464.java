public interface LineageService {

    /**
     * Return the lineage outputs graph for the given datasetName.
     *
     * @param datasetName datasetName
     * @return Outputs Graph as JSON
     */
    String getOutputsGraph(String datasetName) throws AtlasException;

    /**
     * Return the lineage inputs graph for the given datasetName.
     *
     * @param datasetName datasetName
     * @return Inputs Graph as JSON
     */
    String getInputsGraph(String datasetName) throws AtlasException;

    /**
     * Return the lineage inputs graph for the given entity id.
     *
     * @param guid entity id
     * @return Inputs Graph as JSON
     */
    String getInputsGraphForEntity(String guid) throws AtlasException;

    /**
     * Return the lineage inputs graph for the given entity id.
     *
     * @param guid entity id
     * @return Inputs Graph as JSON
     */
    String getOutputsGraphForEntity(String guid) throws AtlasException;

    /**
     * Return the schema for the given datasetName.
     *
     * @param datasetName datasetName
     * @return Schema as JSON
     */
    String getSchema(String datasetName) throws AtlasException;

    /**
     * Return the schema for the given entity id.
     *
     * @param guid tableName
     * @return Schema as JSON
     */
    String getSchemaForEntity(String guid) throws AtlasException;
}
