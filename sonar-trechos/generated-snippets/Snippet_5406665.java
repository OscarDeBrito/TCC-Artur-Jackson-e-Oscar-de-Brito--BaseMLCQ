@IsProtected
public class GetJndi implements Command {

    @Override
    public Object execute(final Map<String, Object> params) throws Exception {
        final String sessionId = (String) params.get("sessionId");
        final List<String> jndi = new ArrayList<String>();

        ContainerSystem container = SystemInstance.get().getComponent(ContainerSystem.class);
        BeanContext[] deployments = container.deployments();
        if (deployments != null) {
            for (BeanContext beanContext : deployments) {
                jndi.add(String.valueOf(beanContext.getDeploymentID()));
            }
        }

        final Map<String, Object> json = new HashMap<String, Object>();
        json.put("jndi", jndi);
        return json;
    }
}
