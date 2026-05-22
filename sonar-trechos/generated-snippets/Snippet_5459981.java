@Parameters(commandDescription = "Grant permissions on a namespace")
    private class GrantPermissions extends CliCommand {
        @Parameter(description = "tenant/namespace", required = true)
        private java.util.List<String> params;

        @Parameter(names = "--role", description = "Client role to which grant permissions", required = true)
        private String role;

        @Parameter(names = "--actions", description = "Actions to be granted (produce,consume)", required = true, splitter = CommaParameterSplitter.class)
        private List<String> actions;

        @Override
        void run() throws PulsarAdminException {
            String namespace = validateNamespace(params);
            admin.namespaces().grantPermissionOnNamespace(namespace, role, getAuthActions(actions));
        }
    }
