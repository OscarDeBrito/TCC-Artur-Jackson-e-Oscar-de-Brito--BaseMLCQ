public class AuthorizationManagerAllowAll implements AuthorizationManager {

    @Override
    public void init(final DeploymentCategory deploymentCategory) {
    }

    @Override
    public void shutdown() {
    }

    @Override
    public boolean isUsable(final AuthenticationSession session, final ObjectAdapter target, final Identifier identifier) {
        return true;
    }

    @Override
    public boolean isVisible(final AuthenticationSession session, final ObjectAdapter target, final Identifier identifier) {
        return true;
    }

}
