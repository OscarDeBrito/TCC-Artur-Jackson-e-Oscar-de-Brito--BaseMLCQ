@APICommand(name = "listCapabilities", description = "Lists capabilities", responseObject = CapabilitiesResponse.class,
        requestHasSensitiveInfo = false, responseHasSensitiveInfo = false)
public class ListCapabilitiesCmd extends BaseCmd {
    public static final Logger s_logger = Logger.getLogger(ListCapabilitiesCmd.class.getName());

    private static final String s_name = "listcapabilitiesresponse";

    @Override
    public String getCommandName() {
        return s_name;
    }

    @Override
    public long getEntityOwnerId() {
        return Account.ACCOUNT_ID_SYSTEM;
    }

    @Override
    public void execute() {
        Map<String, Object> capabilities = _mgr.listCapabilities(this);
        CapabilitiesResponse response = new CapabilitiesResponse();
        response.setSecurityGroupsEnabled((Boolean)capabilities.get("securityGroupsEnabled"));
        response.setDynamicRolesEnabled(roleService.isEnabled());
        response.setCloudStackVersion((String)capabilities.get("cloudStackVersion"));
        response.setUserPublicTemplateEnabled((Boolean)capabilities.get("userPublicTemplateEnabled"));
        response.setSupportELB((String)capabilities.get("supportELB"));
        response.setProjectInviteRequired((Boolean)capabilities.get("projectInviteRequired"));
        response.setAllowUsersCreateProjects((Boolean)capabilities.get("allowusercreateprojects"));
        response.setDiskOffMinSize((Long)capabilities.get("customDiskOffMinSize"));
        response.setDiskOffMaxSize((Long)capabilities.get("customDiskOffMaxSize"));
        response.setRegionSecondaryEnabled((Boolean)capabilities.get("regionSecondaryEnabled"));
        response.setKVMSnapshotEnabled((Boolean)capabilities.get("KVMSnapshotEnabled"));
        response.setAllowUserViewDestroyedVM((Boolean)capabilities.get("allowUserViewDestroyedVM"));
        response.setAllowUserExpungeRecoverVM((Boolean)capabilities.get("allowUserExpungeRecoverVM"));
        if (capabilities.containsKey("apiLimitInterval")) {
            response.setApiLimitInterval((Integer)capabilities.get("apiLimitInterval"));
        }
        if (capabilities.containsKey("apiLimitMax")) {
            response.setApiLimitMax((Integer)capabilities.get("apiLimitMax"));
        }
        response.setObjectName("capability");
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
    }
}
