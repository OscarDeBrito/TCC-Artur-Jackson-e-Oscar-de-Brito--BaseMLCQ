public class Snippet__5580203 {

    private void routeToBackend(RoutingContext context, Hcr hcr, String userId) {
        Map<String, String> pathParams = context.pathParams();
        String microserviceName = pathParams.get("param0");
        String pathVersion = pathParams.get("param1");
        String path = context.request().path().substring(prefix.length() + 1);

        EncryptEdgeInvocation edgeInvocation = new EncryptEdgeInvocation(new EncryptContext(hcr, userId));
        edgeInvocation.setVersionRule(versionMapper.getOrCreate(pathVersion).getVersionRule());

        edgeInvocation.init(microserviceName, context, path, httpServerFilters);
        edgeInvocation.edgeInvoke();
      }

}
