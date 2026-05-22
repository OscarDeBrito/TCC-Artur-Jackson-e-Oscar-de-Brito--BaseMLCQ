public class JdbcMetadataDaoImpl implements IMetadataDao {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcMetadataDaoImpl.class);
    private JdbcMetadataHandler handler;

    @Inject
    public JdbcMetadataDaoImpl(Config config) {
        handler = new JdbcMetadataHandler(config.getConfig(MetadataUtils.META_DATA));
    }

    @Override
    public List<Topology> listTopologies() {
        return handler.list(Topology.class);
    }

    @Override
    public List<StreamingCluster> listClusters() {
        return handler.list(StreamingCluster.class);
    }

    @Override
    public List<StreamDefinition> listStreams() {
        return handler.list(StreamDefinition.class);
    }

    @Override
    public List<Kafka2TupleMetadata> listDataSources() {
        return handler.list(Kafka2TupleMetadata.class);
    }

    @Override
    public List<PolicyDefinition> listPolicies() {
        return handler.list(PolicyDefinition.class);
    }

    @Override
    public List<Publishment> listPublishment() {
        return handler.listPublishments();
    }

    @Override
    public List<AlertPublishEvent> listAlertPublishEvent(int size) {
        if (size <= 0) {
            LOG.info("Invalid parameter size <= 0");
            return new ArrayList<>();
        }
        return handler.listAlertEvents(null, null, size);
    }

    public PolicyDefinition getPolicyById(String policyId) {
        return handler.queryById(PolicyDefinition.class, policyId);
    }

    public List<Publishment> getPublishmentsByPolicyId(String policyId) {
        return handler.getPublishmentsByPolicyId(policyId);
    }

    @Override
    public AlertPublishEvent getAlertPublishEvent(String alertId) {
        return handler.getAlertEventById(alertId, 1);
    }

    @Override
    public List<AlertPublishEvent> getAlertPublishEventsByPolicyId(String policyId, int size) {
        if (size <= 0) {
            LOG.info("Invalid parameter size <= 0");
            return new ArrayList<>();
        }
        return handler.getAlertEventByPolicyId(policyId, size);
    }

    @Override
    public ScheduleState getScheduleState(String versionId) {
        return handler.queryById(ScheduleState.class, versionId);
    }

    @Override
    public ScheduleState getScheduleState() {
        List<ScheduleState> scheduleStates =
                handler.list(ScheduleState.class, JdbcMetadataHandler.SortType.DESC);
        if (scheduleStates.isEmpty()) {
            return null;
        } else {
            return scheduleStates.get(0);
        }
    }

    @Override
    public List<ScheduleState> listScheduleStates() {
        return handler.list(ScheduleState.class);
    }

    @Override
    public List<PolicyAssignment> listAssignments() {
        return handler.list(PolicyAssignment.class);
    }

    @Override
    public List<PublishmentType> listPublishmentType() {
        return handler.list(PublishmentType.class);
    }

    @Override
    public OpResult addTopology(Topology t) {
        return handler.addOrReplace(Topology.class.getSimpleName(), t);
    }

    @Override
    public OpResult addCluster(StreamingCluster cluster) {
        return handler.addOrReplace(StreamingCluster.class.getSimpleName(), cluster);
    }

    @Override
    public OpResult addAlertPublishEvent(AlertPublishEvent event) {
        return handler.addAlertEvent(event);
    }

    @Override
    public OpResult createStream(StreamDefinition stream) {
        return handler.addOrReplace(StreamDefinition.class.getSimpleName(), stream);
    }

    @Override
    public OpResult addDataSource(Kafka2TupleMetadata dataSource) {
        return handler.addOrReplace(Kafka2TupleMetadata.class.getSimpleName(), dataSource);
    }

    @Override
    public OpResult addPolicy(PolicyDefinition policy) {
        return handler.addOrReplace(PolicyDefinition.class.getSimpleName(), policy);
    }

    @Override
    public OpResult addPublishment(Publishment publishment) {
        return handler.addOrReplace(Publishment.class.getSimpleName(), publishment);
    }

    @Override
    public OpResult addPublishmentsToPolicy(String policyId, List<String> publishmentIds) {
        return handler.addPublishmentsToPolicy(policyId, publishmentIds);
    }

    @Override
    public OpResult addScheduleState(ScheduleState state) {
        return handler.addOrReplace(ScheduleState.class.getSimpleName(), state);
    }

    @Override
    public OpResult clearScheduleState(int maxCapacity) {
        if (maxCapacity <= 0) {
            maxCapacity = 10;
        }
        OpResult result = handler.removeScheduleStates(maxCapacity);
        LOG.info(result.message);
        return result;
    }

    @Override
    public OpResult addAssignment(PolicyAssignment assignment) {
        return handler.addOrReplace(PolicyAssignment.class.getSimpleName(), assignment);
    }

    @Override
    public OpResult addPublishmentType(PublishmentType publishmentType) {
        return handler.addOrReplace(PublishmentType.class.getSimpleName(), publishmentType);
    }

    @Override
    public OpResult removeTopology(String topologyName) {
        return handler.removeById(Topology.class.getSimpleName(), topologyName);
    }

    @Override
    public OpResult removeCluster(String clusterId) {
        return handler.removeById(StreamingCluster.class.getSimpleName(), clusterId);
    }

    @Override
    public OpResult removeStream(String streamId) {
        return handler.removeById(StreamDefinition.class.getSimpleName(), streamId);
    }

    @Override
    public OpResult removeDataSource(String datasourceId) {
        return handler.removeById(Kafka2TupleMetadata.class.getSimpleName(), datasourceId);
    }

    @Override
    public OpResult removePolicy(String policyId) {
        //return handler.removePolicyById(PolicyDefinition.class.getSimpleName(), policyId);
        return handler.removeById(PolicyDefinition.class.getSimpleName(), policyId);
    }

    @Override
    public OpResult removePublishment(String pubId) {
        return handler.removeById(Publishment.class.getSimpleName(), pubId);
    }

    @Override
    public OpResult removePublishmentType(String name) {
        return handler.removeById(PublishmentType.class.getSimpleName(), name);
    }

    @Override
    public OpResult clear() {
        throw new UnsupportedOperationException("clear not support!");
    }

    @Override
    public Models export() {
        throw new UnsupportedOperationException("clear not support!");
    }

    @Override
    public OpResult importModels(Models models) {
        throw new UnsupportedOperationException("clear not support!");
    }

    @Override
    public void close() throws IOException {
        if (handler != null) {
            handler.close();
        }
    }
}
