public class Snippet__3851209 {

    private ResourceInstance createConfigResource() {
        Map<Resource.Type, String> mapIds = new HashMap<>();
        mapIds.put(Resource.Type.RootService, RootService.AMBARI.name());
        mapIds.put(Resource.Type.RootServiceComponent, RootComponent.AMBARI_SERVER.name());

        return createResource(Resource.Type.RootServiceComponentConfiguration, mapIds);
      }

}
