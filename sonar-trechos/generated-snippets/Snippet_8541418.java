@APICommand(name = EnableHAForHostCmd.APINAME, description = "Enables HA for a host",
        responseObject = HostHAResponse.class,
        requestHasSensitiveInfo = false, responseHasSensitiveInfo = false,
        since = "4.11", authorized = {RoleType.Admin})
public final class EnableHAForHostCmd extends BaseAsyncCmd {
    public static final String APINAME = "enableHAForHost";

    @Inject
    private HAConfigManager haConfigManager;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ApiConstants.HOST_ID, type = CommandType.UUID, entityType = HostResponse.class,
            description = "ID of the host", required = true, validations = {ApiArgValidator.PositiveNumber})
    private Long hostId;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public Long getHostId() {
        return hostId;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @Override
    public String getCommandName() {
        return APINAME.toLowerCase() + BaseCmd.RESPONSE_SUFFIX;
    }

    @Override
    public long getEntityOwnerId() {
        return CallContext.current().getCallingAccountId();
    }

    private void setupResponse(final boolean result, final String resourceUuid) {
        final HostHAResponse response = new HostHAResponse();
        response.setId(resourceUuid);
        response.setEnabled(true);
        response.setStatus(result);
        response.setResponseName(getCommandName());
        setResponseObject(response);
    }

    @Override
    public void execute() throws ResourceUnavailableException, InsufficientCapacityException, ServerApiException, ConcurrentOperationException, ResourceAllocationException, NetworkRuleConflictException {
        final Host host = _resourceService.getHost(getHostId());
        if (host == null) {
            throw new ServerApiException(ApiErrorCode.PARAM_ERROR, "Unable to find host by ID: " + getHostId());
        }
        final boolean result = haConfigManager.enableHA(host.getId(), HAResource.ResourceType.Host);

        CallContext.current().setEventDetails("Host Id:" + host.getId() + " HA enabled: true");
        CallContext.current().putContextParameter(Host.class, host.getUuid());

        setupResponse(result, host.getUuid());
    }

    @Override
    public String getEventType() {
        return EventTypes.EVENT_HA_RESOURCE_ENABLE;
    }

    @Override
    public String getEventDescription() {
        return "enable HA for host: " + getHostId();
    }
}
