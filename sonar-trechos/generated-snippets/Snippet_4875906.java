public class Snippet__4875906 {

    public static void updateResourceConfigsFor(Map<String, String> newConfigs, String resourceName, String clusterName,
          HelixAdmin admin) {
        final HelixConfigScope scope = getResourceScopeFor(clusterName, resourceName);
        admin.setConfig(scope, newConfigs);
      }

}
