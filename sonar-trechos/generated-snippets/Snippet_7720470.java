@Api(tags = {PKS_CLUSTER_LIST_TAG})
@Path("")
public class PKSClusterListService extends StatelessService {

    public static final String SELF_LINK = ManagementUriParts.PKS_CLUSTERS;

    static {
        ReflectionUtils.setAnnotation(PKSClusterListService.class, Path.class,
                new CustomPath(SELF_LINK));
    }

    @Override
    public void handleRequest(Operation op) {
        if (op.getAction() != Action.GET) {
            Operation.failActionNotSupported(op);
            return;
        }

        handleGet(op);
    }

    @Override
    @GET
    @Path(BASE_PATH)
    @ApiOperation(
            value = "List one or all PKS clusters",
            notes = "Retrieves all PKS clusters if no endpoint link is supplied." + LINE_BREAK + LINE_BREAK +
                    "Retrieves a single PKS cluster when an enpoint link is supplied.",
            nickname = "getSingleOrAll",
            response = PKSCluster.class,
            responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = Operation.STATUS_CODE_OK, message = ""),
            @ApiResponse(code = Operation.STATUS_CODE_NOT_FOUND, message = "")})
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = PKS_ENDPOINT_QUERY_PARAM_NAME,
                    value = "The endpoint link from which to retrieve the PKS cluster/s.",
                    dataType = DATA_TYPE_STRING,
                    paramType = PARAM_TYPE_QUERY,
                    required = true),
            @ApiImplicitParam(
                    name = PKS_CLUSTER_QUERY_PARAM_NAME,
                    value = "The name of the cluster to retrieve. If supplied, retrieves only the specified cluster.",
                    dataType = DATA_TYPE_STRING,
                    paramType = PARAM_TYPE_QUERY)})
    public void handleGet(Operation op) {
        try {
            Map<String, String> queryParams = UriUtils.parseUriQueryParams(op.getUri());

            String endpointLink = queryParams.get(PKS_ENDPOINT_QUERY_PARAM_NAME);
            AssertUtil.assertNotNullOrEmpty(endpointLink, PKS_ENDPOINT_QUERY_PARAM_NAME);

            String clusterName = queryParams.get(PKS_CLUSTER_QUERY_PARAM_NAME);

            if (clusterName != null && !clusterName.isEmpty()) {
                handleGetRequest(op, endpointLink, clusterName);
            } else {
                handleListRequest(op, endpointLink);
            }
        } catch (Exception x) {
            logSevere(x);
            op.fail(x);
        }
    }

    private void handleGetRequest(Operation op, String endpointLink, String clusterName) {
        AdapterRequest request = new AdapterRequest();
        request.operationTypeId = PKSOperationType.GET_CLUSTER.id;
        request.serviceTaskCallback = ServiceTaskCallback.createEmpty();
        request.resourceReference = UriUtils.buildUri(getHost(), endpointLink);
        request.customProperties = new HashMap<>(2);
        request.customProperties.put(PKS_CLUSTER_NAME_PROP_NAME, clusterName);

        sendRequest(Operation.createPatch(getHost(), ManagementUriParts.ADAPTER_PKS)
                .setBodyNoCloning(request)
                .setCompletion((o, ex) -> {
                    if (ex != null) {
                        logSevere("Adapter request for get PKS cluster failed. Error: %s",
                                Utils.toString(ex));
                        op.fail(ex);
                    } else {
                        PKSCluster pksCluster = o.getBody(PKSCluster.class);
                        PKSCluster[] pksClusters = new PKSCluster[] { pksCluster };
                        queryComputes(op, pksClusters, endpointLink);
                    }
                }));
    }

    private void handleListRequest(Operation op, String endpointLink) {
        AdapterRequest request = new AdapterRequest();
        request.operationTypeId = PKSOperationType.LIST_CLUSTERS.id;
        request.serviceTaskCallback = ServiceTaskCallback.createEmpty();
        request.resourceReference = UriUtils.buildUri(getHost(), endpointLink);

        sendRequest(Operation.createPatch(getHost(), ManagementUriParts.ADAPTER_PKS)
                .setBodyNoCloning(request)
                .setCompletion((o, ex) -> {
                    if (ex != null) {
                        logSevere("Adapter request for listing PKS clusters failed. Error: %s",
                                Utils.toString(ex));
                        op.fail(ex);
                    } else {
                        PKSCluster[] pksClusters = o.getBody(PKSCluster[].class);
                        queryComputes(op, pksClusters, endpointLink);
                    }
                }));
    }

    private void queryComputes(Operation op, PKSCluster[] pksClusters, String endpointLink) {
        QueryTask.Query endpointClause = new QueryTask.Query()
                .setTermPropertyName(QuerySpecification.buildCompositeFieldName(
                        ResourceState.FIELD_NAME_CUSTOM_PROPERTIES,
                        PKS_ENDPOINT_PROP_NAME))
                .setTermMatchValue(endpointLink);

        QueryTask queryTask = QueryUtil.buildQuery(ComputeState.class, true, endpointClause);
        QueryUtil.addExpandOption(queryTask);

        Set<String> uuids = new HashSet<>();
        new ServiceDocumentQuery<>(getHost(), ComputeState.class)
                .query(queryTask, (r) -> {
                    if (r.hasException()) {
                        logSevere("Failed to query for compute states with endpoint link [%s]: %s",
                                endpointLink, Utils.toString(r.getException()));
                        op.fail(r.getException());
                    } else if (r.hasResult()) {
                        String uuid = PropertyUtils.getPropertyString(
                                r.getResult().customProperties, PKS_CLUSTER_UUID_PROP_NAME)
                                .orElse(null);
                        if (uuid != null) {
                            uuids.add(uuid);
                        }
                    } else {
                        processHosts(op, uuids, pksClusters);
                    }
                });
    }

    private void processHosts(Operation op, Set<String> uuids, PKSCluster[] pksClusters) {
        for (PKSCluster cluster: pksClusters) {
            if (uuids.contains(cluster.uuid)) {
                if (cluster.parameters == null) {
                    cluster.parameters = new HashMap<>();
                }
                cluster.parameters.put(PKS_CLUSTER_EXISTS_PROP_NAME, Boolean.TRUE.toString());
            }
        }
        op.setBody(pksClusters);
        op.complete();
    }
}
