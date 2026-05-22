public class Snippet__4112198 {

    protected KubernetesClient getClient(ConfigBag config) {
            if (client == null) {
                KubernetesClientRegistry registry = getConfig(KUBERNETES_CLIENT_REGISTRY);
                client = registry.getKubernetesClient(ResolvingConfigBag.newInstanceExtending(getManagementContext(), config));
            }
            return client;
        }

}
