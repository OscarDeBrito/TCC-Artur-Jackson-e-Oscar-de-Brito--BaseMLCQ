public class DeploymentTypeWicketAbstract extends DeploymentTypeAbstract {

    public DeploymentTypeWicketAbstract(
            final String name, final DeploymentCategory deploymentCategory) {
        super(name, deploymentCategory);
    }

    public RuntimeConfigurationType getConfigurationType() {
        return getDeploymentCategory().isProduction()? RuntimeConfigurationType.DEPLOYMENT: RuntimeConfigurationType.DEVELOPMENT;
    }


}
